package alex;

import asint.ClaseLexica;

public class ALexOperations {
   private AnalizadorLexicoTiny alex;

   public ALexOperations(AnalizadorLexicoTiny alex) {
      this.alex = alex;
   }

   public UnidadLexica unidadEnt() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ENT, alex.lexema());
   }

   public UnidadLexica unidadSuma() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAS);
   }

   public UnidadLexica unidadResta() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENOS);
   }

   public UnidadLexica unidadMul() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.POR);
   }

   public UnidadLexica unidadDiv() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DIV);
   }

   public UnidadLexica unidadPAp() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PAP);
   }

   public UnidadLexica unidadPCierre() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PCIERRE);
   }

   public UnidadLexica unidadCAp() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CAP);
   }

   public UnidadLexica unidadCCierre() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CCIERRE);
   }

   public UnidadLexica unidadLLAp() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LLAP);
   }

   public UnidadLexica unidadLLCierre() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LLCIERRE);
   }

   public UnidadLexica unidadDosPuntos() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DOSPUNTOS);
   }

   public UnidadLexica unidadPunto() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PUNTO);
   }

   public UnidadLexica unidadFnDef() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FUNDEF);
   }

   public UnidadLexica unidadVarDef() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.VARDEF);
   }

   public UnidadLexica unidadIgual() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IGUAL);
   }

   public UnidadLexica unidadComa() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.COMA);
   }

   public UnidadLexica unidadPuntoComa() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PUNTOCOMA);
   }

   public UnidadLexica unidadMayorIgual() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAYORIGUAL);
   }

   public UnidadLexica unidadMayor() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAYOR);
   }

   public UnidadLexica unidadMenorIgual() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENORIGUAL);
   }

   public UnidadLexica unidadMenor() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENOR);
   }

   public UnidadLexica unidadAmpersand() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.AMPERSAND);
   }

   public UnidadLexica unidadDolar() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DOLAR);
   }

   public UnidadLexica unidadAnd() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.AND);
   }

   public UnidadLexica unidadOr() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.OR);
   }

   public UnidadLexica unidadNot() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NOT);
   }

   public UnidadLexica unidadMod() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MOD);
   }

   public UnidadLexica unidadAsign() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ASIGN);
   }

   public UnidadLexica unidadIf() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IF);
   }

   public UnidadLexica unidadElse() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ELSE);
   }

   public UnidadLexica unidadFor() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FOR);
   }

   public UnidadLexica unidadWhile() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.WHILE);
   }

   public UnidadLexica unidadReturn() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.RETURN);
   }

   public UnidadLexica unidadNew() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NEW);
   }

   public UnidadLexica unidadDelete() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DELETE);
   }

   public UnidadLexica unidadBool() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.BOOL);
   }

   public UnidadLexica unidadInt() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.INT);
   }

   public UnidadLexica unidadType() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.TYPEDEF);
   }

   public UnidadLexica unidadStruct() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.STRUCT);
   }

   public UnidadLexica unidadTrue() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.TRUE);
   }

   public UnidadLexica unidadFalse() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FALSE);
   }

   public UnidadLexica unidadPrint() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PRINT);
   }

   public UnidadLexica unidadScan() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.SCAN);
   }

   public UnidadLexica unidadEof() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.EOF);
   }

   public UnidadLexica unidadId() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IDEN,
            alex.lexema());
   }

   public void error() {
      System.err.println("***" + alex.fila() + ", " + alex.columna() + " Caracter inesperado: " + alex.lexema());
   }
}
