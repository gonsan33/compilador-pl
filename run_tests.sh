#!/bin/bash

CP_SEP=":"
case "$(uname -s)" in
    CYGWIN*|MINGW*|MSYS*) CP_SEP=";" ;;
esac
CP=".${CP_SEP}cup.jar"

# Generar lexer en caso de que no lo esté ya (no lo incluimos en el repo porque es algo "generable")
if [ ! -f alex/AnalizadorLexicoTiny.java ]; then
    echo "Generado lexer apartr de lexico.l"
    java -cp jflex.jar jflex.Main lexico.l
    mv AnalizadorLexicoTiny.java alex/
fi

# Generar parser en caso de que no lo esté ya (no lo incluimos en el repo porque es algo "generable")
if [ ! -f asint/AnalizadorSintacticoTiny.java ]; then
    echo "Generadno parser apartir de Tiny.cup"
    java -cp cup.jar java_cup.Main -parser AnalizadorSintacticoTiny -symbols ClaseLexica -nopositions -expect 38 Tiny.cup
    mv AnalizadorSintacticoTiny.java asint/
    mv ClaseLexica.java asint/
fi

# Compilar todo
if ! javac -encoding UTF-8 -cp "$CP" */*.java Main.java tests/tests_ast/ASTTest.java tests/tests_type/TypeTest.java; then
    echo "Compilacion fallida: no se ejecutan los tests." >&2
    exit 1
fi

echo "Testear Léxico"
LEX_DIR="tests/tests_lexico"
lex_ok=0
lex_fail=0
if [ -d "$LEX_DIR" ]; then
    for f in "$LEX_DIR"/*.in; do
        [ -f "$f" ] || continue
        output=$(java -cp "$CP" alex.Main "$f" 2>&1)
        exit_code=$?
        case "$(basename "$f")" in
            *"_err"*|*"invalid"*) expects_error=1 ;;
            *) expects_error=0 ;;
        esac
        if printf '%s\n' "$output" | grep -Eq "ERROR|Caracter inesperado"; then
            has_error=1
        else
            has_error=0
        fi
        if [ $exit_code -eq 0 ] && [ $expects_error -eq $has_error ]; then
            echo "  OK   $f"
            lex_ok=$((lex_ok + 1))
        else
            echo "  FAIL $f (exit $exit_code, expected lexical error: $expects_error, found lexical error: $has_error)"
            printf '%s\n' "$output"
            lex_fail=$((lex_fail + 1))
        fi
    done
fi
echo "--- Resultados léxico: $lex_ok OK, $lex_fail FAIL de $((lex_ok + lex_fail)) ficheros ---"
if [ $lex_fail -gt 0 ]; then
    exit 1
fi

echo ""
echo "Testear AST"
java -cp "$CP" tests.tests_ast.ASTTest
echo ""

echo "Testear Binding"
BIND_DIR="tests/tests_binding"
bind_ok=0
bind_fail=0

if [ -d "$BIND_DIR" ]; then
    for f in "$BIND_DIR"/*.in; do
        [ -f "$f" ] || continue
        output=$(java -cp "$CP" Main "$f" 2>&1)
        exit_code=$?
        case "$(basename "$f")" in
            *"_err"*|*"undeclared"*|*"no_visible"*) expects_error=1 ;;
            *) expects_error=0 ;;
        esac
        if printf '%s\n' "$output" | grep -q "ERROR BINDING"; then
            has_error=1
        else
            has_error=0
        fi
        if [ $exit_code -eq 0 ] && [ $expects_error -eq $has_error ]; then
            echo "  OK   $f"
            bind_ok=$((bind_ok + 1))
        else
            echo "  FAIL $f (exit $exit_code, expected binding error: $expects_error, found binding error: $has_error)"
            printf '%s\n' "$output"
            bind_fail=$((bind_fail + 1))
        fi
    done
fi
echo "--- Resultados binding: $bind_ok OK, $bind_fail FAIL de $((bind_ok + bind_fail)) ficheros ---"
if [ $bind_fail -gt 0 ]; then
    exit 1
fi

echo ""
echo "Testear Tipado"
java -cp "$CP" tests.tests_type.TypeTest
