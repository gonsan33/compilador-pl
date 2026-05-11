#!/bin/bash

CP_SEP=":"
case "$(uname -s)" in
    CYGWIN*|MINGW*|MSYS*) CP_SEP=";" ;;
esac
CP=".${CP_SEP}cup.jar"

generate_sources() {
    if [ ! -f alex/AnalizadorLexicoTiny.java ] || [ lexico.l -nt alex/AnalizadorLexicoTiny.java ]; then
        java -cp jflex.jar jflex.Main lexico.l
        mv AnalizadorLexicoTiny.java alex/
    fi

    if [ ! -f asint/AnalizadorSintacticoTiny.java ] || [ ! -f asint/ClaseLexica.java ] || [ Tiny.cup -nt asint/AnalizadorSintacticoTiny.java ]; then
        java -cp cup.jar java_cup.Main -parser AnalizadorSintacticoTiny -symbols ClaseLexica -nopositions Tiny.cup
        mv AnalizadorSintacticoTiny.java asint/
        mv ClaseLexica.java asint/
    fi
}

compile_project() {
    javac -encoding UTF-8 -cp "$CP" */*.java Main.java tests/tests_ast/ASTTest.java tests/tests_type/TypeTest.java tests/test_generar_codigo/CodeGenRunner.java
}

run_expected_error_tests() {
    local title="$1"
    local dir="$2"
    local command_class="$3"
    local error_pattern="$4"
    local error_name_regex="$5"
    local ok=0
    local fail=0

    echo ""
    echo "$title"
    for f in "$dir"/*.in; do
        [ -f "$f" ] || continue

        output=$(java -cp "$CP" "$command_class" "$f" 2>&1)
        exit_code=$?

        if [[ "$(basename "$f")" =~ $error_name_regex ]]; then
            expects_error=1
        else
            expects_error=0
        fi

        if printf '%s\n' "$output" | grep -Eq "$error_pattern"; then
            has_error=1
        else
            has_error=0
        fi

        if [ $exit_code -eq 0 ] && [ $expects_error -eq $has_error ]; then
            echo "  OK   $f"
            ok=$((ok + 1))
        else
            echo "  FAIL $f"
            printf '%s\n' "$output"
            fail=$((fail + 1))
        fi
    done

    echo "--- $title: $ok OK, $fail FAIL ---"
    [ $fail -eq 0 ]
}

pick_wat2wasm() {
    if [ "$(uname -s)" = "Darwin" ]; then
        echo "./node_modules/.bin/wat2wasm"
    else
        echo "./wat2wasm"
    fi
}

run_codegen_tests() {
    local codegen_dir="tests/test_generar_codigo"
    local build_dir="/tmp/compilador-pl-codegen"
    local wat2wasm
    local ok=0
    local fail=0

    echo ""
    echo "Testear Generacion de Codigo"

    wat2wasm=$(pick_wat2wasm)
    if [ ! -x "$wat2wasm" ]; then
        echo "  FAIL codegen: no se encontro $wat2wasm"
        return 1
    fi

    mkdir -p "$build_dir"

    for case_dir in "$codegen_dir"/*; do
        [ -d "$case_dir" ] || continue
        [ -f "$case_dir/program.in" ] || continue
        [ -f "$case_dir/expected.out" ] || continue

        case_name=$(basename "$case_dir")
        wat="$build_dir/$case_name.wat"
        wasm="$build_dir/$case_name.wasm"

        if ! java -cp "$CP" tests.test_generar_codigo.CodeGenRunner "$case_dir/program.in" "$wat" >/dev/null; then
            echo "  FAIL $case_name: no se pudo generar WAT"
            fail=$((fail + 1))
            continue
        fi

        if ! "$wat2wasm" "$wat" -o "$wasm"; then
            echo "  FAIL $case_name: wat2wasm rechazo el WAT"
            fail=$((fail + 1))
            continue
        fi

        if [ -f "$case_dir/input.txt" ]; then
            output=$(node "$codegen_dir/run_wasm.js" "$wasm" < "$case_dir/input.txt" 2>&1)
        else
            output=$(node "$codegen_dir/run_wasm.js" "$wasm" 2>&1)
        fi

        expected=$(cat "$case_dir/expected.out")
        if [ "$output" = "$expected" ]; then
            echo "  OK   $case_name"
            ok=$((ok + 1))
        else
            echo "  FAIL $case_name"
            echo "  Esperado:"
            printf '%s\n' "$expected"
            echo "  Obtenido:"
            printf '%s\n' "$output"
            fail=$((fail + 1))
        fi
    done

    echo "--- Generacion de Codigo: $ok OK, $fail FAIL ---"
    [ $fail -eq 0 ]
}

generate_sources || exit 1
compile_project || {
    echo "Compilacion fallida: no se ejecutan los tests." >&2
    exit 1
}

run_expected_error_tests "Testear Lexico" "tests/tests_lexico" "alex.Main" "ERROR|Caracter inesperado" "(_err|invalid)" || exit 1

echo ""
echo "Testear AST"
java -cp "$CP" tests.tests_ast.ASTTest || exit 1

run_expected_error_tests "Testear Binding" "tests/tests_binding" "Main" "ERROR BINDING" "(_err|undeclared|no_visible)" || exit 1

echo ""
echo "Testear Tipado"
java -cp "$CP" tests.tests_type.TypeTest || exit 1

run_codegen_tests || exit 1
