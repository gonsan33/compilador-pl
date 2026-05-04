package tests.tests_type;

import alex.AnalizadorLexicoTiny;
import asint.AnalizadorSintacticoTiny;
import ast.ProgramNode;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.util.Arrays;
import java_cup.runtime.Symbol;

public class TypeTest {

    public static void main(String[] args) throws Exception {
        if (args.length > 0 && args[0].equals("--run")) {
            runSingle(args[1]);
            return;
        }

        File dir = new File("tests/tests_type");
        File[] inputs = dir.listFiles((d, name) -> name.endsWith(".in"));
        if (inputs == null || inputs.length == 0) {
            System.out.println(
                "No se encontraron ficheros .in en " + dir.getPath()
            );
            return;
        }

        Arrays.sort(inputs);
        int ok = 0;
        int fail = 0;
        for (File f : inputs) {
            String cp = "." + File.pathSeparator + "cup.jar";
            ProcessBuilder pb = new ProcessBuilder(
                "java",
                "-cp",
                cp,
                "tests.tests_type.TypeTest",
                "--run",
                f.getPath()
            );
            pb.redirectErrorStream(true);
            Process proc = pb.start();

            StringBuilder output = new StringBuilder();
            try (
                BufferedReader br = new BufferedReader(
                    new InputStreamReader(proc.getInputStream())
                )
            ) {
                String line;
                while ((line = br.readLine()) != null) {
                    output.append(line).append(System.lineSeparator());
                }
            }

            int exit = proc.waitFor();
            boolean expectsError = f.getName().contains("_err");
            boolean hasTypeError = output
                .toString()
                .contains("ERROR TYPECHECK");
            if (exit == 0 && expectsError == hasTypeError) {
                System.out.println("  OK   " + f.getName());
                ok++;
            } else {
                System.out.println(
                    "  FAIL " +
                        f.getName() +
                        " (exit " +
                        exit +
                        ", expected type error: " +
                        expectsError +
                        ", found type error: " +
                        hasTypeError +
                        ")"
                );
                System.out.print(output);
                fail++;
            }
        }
        System.out.println(
            "--- Resultados tipado: " +
                ok +
                " OK, " +
                fail +
                " FAIL de " +
                (ok + fail) +
                " ficheros ---"
        );
        if (fail > 0) {
            System.exit(1);
        }
    }

    private static void runSingle(String path) throws Exception {
        ByteArrayOutputStream captured = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(captured));
        try {
            Reader input = new InputStreamReader(new FileInputStream(path));
            AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
            AnalizadorSintacticoTiny parser = new AnalizadorSintacticoTiny(
                alex
            );
            Symbol result = parser.parse();
            ProgramNode program = (ProgramNode) result.value;
            program.bind();
            program.typeCheck();
        } finally {
            System.setOut(originalOut);
        }
        System.out.print(captured.toString());
    }
}
