package errors;

import alex.UnidadLexica;

public class GestionErroresTiny {
   public void errorLexico(int fila, String lexema) {
     System.out.println("ERROR lexico fila "+fila+": Caracter inexperado: "+lexema); 
     System.exit(1);
   }  
   public void errorSintactico(UnidadLexica unidadLexica) {
     System.out.print("ERROR sintactico fila "+unidadLexica.fila()+": Elemento inexperado "+unidadLexica.value);
     System.exit(1);
   }
   public static void errorTipos(int fila, String tipo){
	 System.out.println("ERROR de tipos fila "+fila+": "+tipo);
	 System.exit(1);
   }
}
