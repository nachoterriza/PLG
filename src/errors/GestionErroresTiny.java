package errors;

import alex.UnidadLexica;

public class GestionErroresTiny {
   public void errorLexico(int fila, String lexema) {
     System.out.println("ERROR fila "+fila+": Caracter inexperado: "+lexema); 
     System.exit(1);
   }  
   public void errorSintactico(UnidadLexica unidadLexica) {
     System.out.print("ERROR fila "+unidadLexica.fila()+": Elemento inexperado "+unidadLexica.value);
     System.exit(1);
   }
   public void errorTipos(int fila, String tipo){
	 System.out.println("ERROR fila "+fila+": Tipo erróneo "+tipo);
	 System.exit(1);
   }
}
