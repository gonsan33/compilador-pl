# Comandos importantes

Los comandos siguientes se ejecutan desde la raiz del proyecto.

En Linux/macOS usa `.:cup.jar` como classpath. En Windows usa `.;cup.jar`.

## Generar fuentes del lexico y del sintactico

Generar el analizador lexico con JFlex:

```sh
java -cp jflex.jar jflex.Main lexico.l
mv AnalizadorLexicoTiny.java alex/
```

Generar el analizador sintactico con CUP:

```sh
java -cp cup.jar java_cup.Main -parser AnalizadorSintacticoTiny -symbols ClaseLexica -nopositions Tiny.cup
mv AnalizadorSintacticoTiny.java asint/
mv ClaseLexica.java asint/
```

## Compilar

Compilar el proyecto, incluyendo el comando de generacion de codigo:

```sh
javac -encoding UTF-8 -cp ".:cup.jar" Main.java */*.java tests/tests_ast/ASTTest.java tests/tests_type/TypeTest.java
```

En Windows:

```sh
javac -encoding UTF-8 -cp ".;cup.jar" Main.java */*.java tests/tests_ast/ASTTest.java tests/tests_type/TypeTest.java
```

## Ejecutar el lexico

```sh
java -cp ".:cup.jar" alex.Main tests/tests_lexico/lex_1.in
```

En Windows cambia `.:cup.jar` por `.;cup.jar`.

## Ejecutar el parser

Este comando solo parsea e imprime el AST. No genera ningun `.wat`.

```sh
java -cp ".:cup.jar" asint.Main tests/test_generar_codigo/while_print_10/program.in
```

## Generar WAT desde un .in

La generacion de codigo esta en `codegen.Main`. Este comando parsea, hace binding,
comprueba tipos y escribe el WAT en el fichero indicado:

```sh
java -cp ".:cup.jar" codegen.Main tests/test_generar_codigo/while_print_10/program.in main.wat
```

Si no se indica fichero de salida, se genera `main.wat` en el directorio actual.
Hemos incluído ejemplos de distintos posibles códigos con los que probar dentro de tests/tests_generar_codigo.

## Ejecutar el WAT con JavaScript

Primero hay que convertir el WAT a WASM. Si tienes `wat2wasm` en la raiz del
proyecto o en el PATH:

```sh
./wat2wasm /tmp/program.wat -o /tmp/program.wasm
node run_wasm.js /tmp/program.wasm
```

En Windows, si el binario esta en la raiz:

```sh
./wat2wasm.exe /tmp/program.wat -o /tmp/program.wasm
node run_wasm.js /tmp/program.wasm
```

Tambien se puede ejecutar todo el flujo de generacion de codigo para un `.in`:

```sh
bash run_file.sh tests/test_generar_codigo/while_print_10/program.in
```

## Ejecutar todos los tests

```sh
bash run_tests.sh
```

finalemente `run_tests.sh` es un script que hemos estado usando mientras desarrollábamos la práctica y que ejecuta tests para cada una de las fases.
