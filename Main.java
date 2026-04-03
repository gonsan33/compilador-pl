import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import alex.AnalizadorLexicoTiny;
import asint.AnalizadorSintacticoTiny;
import ast.ProgramNode;
import java_cup.runtime.Symbol;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Uso: java -cp .:cup.jar Main <fichero>");
            System.exit(1);
        }
        Reader input = new InputStreamReader(new FileInputStream(args[0]));
        AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
        AnalizadorSintacticoTiny parser = new AnalizadorSintacticoTiny(alex);
        Symbol result = parser.parse();
        ProgramNode program = (ProgramNode) result.value;
        System.out.println("AST: " + program);
        program.bind();
        System.out.println("Hemos podido hacer el binding todo ok");
    }
}
