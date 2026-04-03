#!/bin/bash

# Generar lexer en caso de que no lo esté ya (no lo incluimos en el repo porque es algo "generable")
if [ ! -f alex/AnalizadorLexicoTiny.java ]; then
    echo "Generado lexer apartr de lexico.l"
    java -cp jflex.jar jflex.Main lexico.l
    mv AnalizadorLexicoTiny.java alex/
fi

# Generar parser en caso de que no lo esté ya (no lo incluimos en el repo porque es algo "generable")
if [ ! -f asint/AnalizadorSintacticoTiny.java ]; then
    echo "Generadno parser apartir de Tiny.cup"
    java -cp cup.jar java_cup.Main -parser AnalizadorSintacticoTiny -symbols ClaseLexica -nopositions Tiny.cup
    mv AnalizadorSintacticoTiny.java asint/
    mv ClaseLexica.java asint/
fi

# Compilar todo
javac -cp ".:cup.jar" */*.java Main.java tests/tests_ast/ASTTest.java 2>/dev/null

echo "=== Tests AST ==="
java -cp ".:cup.jar" tests.tests_ast.ASTTest
echo ""

echo "=== Tests Binding ==="
BIND_DIR="tests/tests_binding"
if [ -d "$BIND_DIR" ]; then
    for f in "$BIND_DIR"/*.in; do
        [ -f "$f" ] || continue
        echo "Testeando $f"
        java -cp ".:cup.jar" Main "$f"
        exit_code=$?
        if [ $exit_code -eq 0 ]; then
            echo "OK"
        else
            echo "FAIL (exit $exit_code)"
        fi
        echo ""
    done
fi
