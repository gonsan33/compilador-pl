Práctica de Procesadores de Lenguaje hecha por Gonzalo Sánchez Montesinos, Víctor Gómez Torres y Daniel Bordeianu Dascal.

## Guía básica del proyecto

- `alex/`: analizador léxico y utilidades de tokens.
- `asint/`: analizador sintáctico generado con CUP y punto de entrada de parser.
- `ast/`: jerarquía de nodos AST y lógica semántica por nodo (`bind`, etc.).
- `bind/`: singleton que maneja la tabla de símbolos.
- `errors/`: gestión de errores semánticos/sintácticos.
- `tests/`: casos de prueba organizados por fase (`tests_ast`, `tests_binding`, `tests_lexico`).
- `Main.java`: ejecuta parseo + binding de un fichero de entrada.
- `run_tests.sh`: script principal para compilar y ejecutar tests.

## Ejecución de tests

### Ejecutar todos los tests (AST + binding)

Desde la raíz:

`bash run_tests.sh`

El script:

1. Genera lexer/parser si faltan (`alex/AnalizadorLexicoTiny.java`, `asint/AnalizadorSintacticoTiny.java`).
2. Compila el proyecto y el test runner de AST.
3. Ejecuta los tests de `tests/tests_ast/*.in`.
4. Ejecuta los tests de binding en `tests/tests_binding/*.in` pasando cada entrada por `Main`.

### Ejecutar un fichero concreto manualmente

- Pipeline principal (parse + binding):
  `java -cp ".:cup.jar" Main tests/tests_binding/bind_ok.in`
- Solo lexer:
  `java -cp ".:cup.jar" alex.Main tests/tests_lexico/lex_1.in`
- Solo parser:
  `java -cp ".:cup.jar" asint.Main tests/tests_ast/ast_1.in`

## ¿Cómo añadir tests?

Simplemente añade un nuevo `.in` con código de ejemplo en la carpeta de la fase que quieras testear, `run_tests.sh` lo detecta automático.