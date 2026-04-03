package tests.tests_ast;

import java.io.*;
import java.util.Arrays;

public class ASTTest {
    public static void main(String[] args) throws Exception {
        if (args.length > 0 && args[0].equals("--run")) {
            runSingle(args[1]);
            return;
        }
        File dir = new File("tests/tests_ast");
        File[] inputs = dir.listFiles((d, name) -> name.endsWith(".in"));
        if (inputs == null || inputs.length == 0) {
            System.out.println("No se encontraron ficheros .in en " + dir.getPath());
            return;
        }
        Arrays.sort(inputs);
        int ok = 0, fail = 0;
        for (File f : inputs) {
            System.out.println("Testeando  " + f.getName() + "");
            ProcessBuilder pb = new ProcessBuilder(
                "java", "-cp", ".:cup.jar",
                "tests.tests_ast.ASTTest", "--run", f.getPath()
            );
            pb.redirectErrorStream(true);
            Process proc = pb.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            int exit = proc.waitFor();
            if (exit == 0) {
                System.out.println("OK");
                ok++;
            } else {
                System.out.println("FAIL (exit " + exit + ")");
                fail++;
            }
            System.out.println();
        }
        System.out.println("--- Resultados: " + ok + " OK, " + fail + " FAIL de " + (ok + fail) + " ficheros ---");
    }

    private static void runSingle(String path) throws Exception {
        Reader input = new InputStreamReader(new FileInputStream(path));
        alex.AnalizadorLexicoTiny alex = new alex.AnalizadorLexicoTiny(input);
        asint.AnalizadorSintacticoTiny asint = new asint.AnalizadorSintacticoTiny(alex);
        java_cup.runtime.Symbol result = asint.parse();
        System.out.println("AST: " + result.value);
    }
}
