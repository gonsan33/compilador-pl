package tests.test_generar_codigo;

import alex.AnalizadorLexicoTiny;
import asint.AnalizadorSintacticoTiny;
import ast.ProgramNode;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.util.Arrays;
import java_cup.runtime.Symbol;

public class CodegenTest {

    public static void main(String[] args) throws Exception {
        if (args.length > 0 && args[0].equals("--run")) {
            runSingle(args[1]);
            return;
        }

        File dir = new File("tests/test_generar_codigo");
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
                "tests.test_generar_codigo.CodegenTest",
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
            boolean hasError = output.toString().contains("ERROR");
            if (exit == 0 && !hasError) {
                System.out.println("  OK   " + f.getName());
                ok++;
            } else {
                System.out.println(
                    "  FAIL " +
                        f.getName() +
                        " (exit " +
                        exit +
                        ")"
                );
                System.out.print(output);
                fail++;
            }
        }
        System.out.println(
            "--- Resultados codegen: " +
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
        try {
            Reader input = new InputStreamReader(new FileInputStream(path));
            AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
            AnalizadorSintacticoTiny parser = new AnalizadorSintacticoTiny(
                alex
            );
            Symbol result = parser.parse();
            ProgramNode program = (ProgramNode) result.value;
            
            // Bind and type check
            program.bind();
            program.typeCheck();
            
            // Generate code
            StringBuilder code = program.generateCode();
            
            // Validate generated code
            if (code == null || code.length() == 0) {
                System.out.println("ERROR: Generated code is empty");
                System.exit(1);
            }
            
            String watCode = code.toString();
            if (!watCode.contains("(module")) {
                System.out.println("ERROR: Generated WAT code doesn't contain (module");
                System.exit(1);
            }
            
            // Write to file
            String baseName = new File(path).getName();
            String outFileName = baseName.replace(".in", ".wat");
            File outDir = new File("tests/test_generar_codigo");
            File outFile = new File(outDir, outFileName);
            
            try (FileWriter fw = new FileWriter(outFile)) {
                fw.write(watCode);
            }
            
            System.out.println("Generated: " + outFile.getName());
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
