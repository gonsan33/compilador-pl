package alex;

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

   public UnidadLexica unidadDosPuntos() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DOSPUNTOS);
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

   public UnidadLexica unidadAmpersand() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.AMPERSAND);
   }

   public UnidadLexica unidadDolar() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DOLAR);
   }

   public UnidadLexica unidadAnd() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.AND);
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
