#!/bin/bash
set -e

WAT2WASM=""

for candidate in "./wat2wasm" "./wat2wasm.exe" "./node_modules/.bin/wat2wasm"; do
  if [ -x "$candidate" ] && "$candidate" --version >/dev/null 2>&1; then
    WAT2WASM="$candidate"
    break
  fi
done

if [ -z "$WAT2WASM" ] && command -v wat2wasm >/dev/null 2>&1; then
  candidate="$(command -v wat2wasm)"
  if "$candidate" --version >/dev/null 2>&1; then
    WAT2WASM="$candidate"
  fi
fi

if [ -z "$WAT2WASM" ] && command -v wat2wasm.exe >/dev/null 2>&1; then
  candidate="$(command -v wat2wasm.exe)"
  if "$candidate" --version >/dev/null 2>&1; then
    WAT2WASM="$candidate"
  fi
fi

if [ -z "$WAT2WASM" ]; then
  echo "No se encontro wat2wasm ni wat2wasm.exe" >&2
  exit 1
fi

java -cp ".:cup.jar" codegen.Main "$1" /tmp/program.wat
"$WAT2WASM" /tmp/program.wat -o /tmp/program.wasm
node run_wasm.js /tmp/program.wasm
