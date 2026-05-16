package errors;

import alex.UnidadLexica;
import java.util.ArrayList;
import java.util.List;

public class GestionErroresTiny {
  private static List<String> errores = new ArrayList<>();
  private static GestionErroresTiny instance = new GestionErroresTiny();

  public static GestionErroresTiny getInstance() {
    return instance;
  }

  public static void reset() {
    errores.clear();
  }

  public static boolean hasErrors() {
    return !errores.isEmpty();
  }

  public static void printErrorsAndExit() {
    if (hasErrors()) {
      System.exit(1);
    }
  }

  public void errorLexico(int fila, int columna, String lexema) {
    String msg = "ERROR fila " + fila + " columna " + columna + ": Caracter inesperado: " + lexema;
    System.out.println(msg);
    errores.add(msg);
  }

  public void errorSintactico(UnidadLexica unidadLexica) {
    String msg;
    if (unidadLexica.lexema() != null) {
      msg = "ERROR fila " + unidadLexica.fila() + " columna " + unidadLexica.columna()
          + ": Elemento inesperado \"" + unidadLexica.lexema() + "\"";
    } else {
      msg = "ERROR fila " + unidadLexica.fila() + " columna " + unidadLexica.columna() + ": Elemento inesperado";
    }
    System.out.println(msg);
    errores.add(msg);
  }

  public void errorBinding(String message) {
    String msg = "ERROR BINDING: " + message;
    System.out.println(msg);
    errores.add(msg);
  }

  public void errorTipos(String message) {
    String msg = "ERROR TYPECHECK: " + message;
    System.out.println(msg);
    errores.add(msg);
  }
}
