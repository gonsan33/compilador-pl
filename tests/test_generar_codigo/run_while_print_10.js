const { readFileSync } = require("fs");

const wasmPath = process.argv[2] || "tests/test_generar_codigo/caca.wasm";
const input = readFileSync(0, "utf8")
  .trim()
  .split(/\s+/)
  .filter(Boolean)
  .map(Number);

let inputIndex = 0;

const imports = {
  runtime: {
    exceptionHandler(code) {
      throw new Error(`Runtime error ${code}`);
    },
    print(value) {
      console.log(value);
    },
    read() {
      return input[inputIndex++];
    },
  },
};

async function main() {
  const wasmBytes = readFileSync(wasmPath);
  const module = await WebAssembly.compile(wasmBytes);
  await WebAssembly.instantiate(module, imports);
}

main().catch((error) => {
  console.error(error);
  process.exit(1);
});
