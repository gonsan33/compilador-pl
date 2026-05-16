package codegen;

import alex.AnalizadorLexicoTiny;
import asint.AnalizadorSintacticoTiny;
import ast.ProgramNode;
import errors.GestionErroresTiny;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java_cup.runtime.Symbol;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 1 || args.length > 2) {
            System.out.println(
                "Uso: java -cp .:cup.jar codegen.Main <entrada.in> [salida.wat]"
            );
            System.exit(1);
        }

        String inputPath = args[0];
        String outputPath = args.length == 2 ? args[1] : "main.wat";

        Reader input = new InputStreamReader(new FileInputStream(inputPath));
        AnalizadorLexicoTiny lexer = new AnalizadorLexicoTiny(input);
        AnalizadorSintacticoTiny parser = new AnalizadorSintacticoTiny(lexer);
        Symbol result = parser.parse();

        ProgramNode program = (ProgramNode) result.value;
        
        program.bind();
        if (GestionErroresTiny.hasErrors()) {
            return; 
        }
        
        program.typeCheck();
        if (GestionErroresTiny.hasErrors()) {
            return;
        }
        
        program.asignarDelta(0);  // Asigna deltas a todas las variables
        if (GestionErroresTiny.hasErrors()) {
            return;
        }

        program.writeWatFile(outputPath);

        System.out.println("WAT generado en: " + outputPath);
    }
}
