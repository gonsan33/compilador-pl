  java -cp ".:cup.jar" tests.test_generar_codigo.CodeGenRunner $1 \
    /tmp/program.wat \
  && ./node_modules/.bin/wat2wasm /tmp/program.wat -o /tmp/program.wasm \
  && node tests/test_generar_codigo/run_wasm.js /tmp/program.wasm
