package errors;

import alex.UnidadLexica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GestionErroresTiny {
	
   public static void setArchivo(String nombre) {
	   archivo = nombre;
   }
   
   private static String sacarContexto(int fila) {
	   try{
		   Scanner sc = new Scanner(new File(archivo));
		   int c = 0;
		   while(c<fila-2) {
			   sc.nextLine();
			   c++;
		   }
		   String error = new String(sc.nextLine()+sc.nextLine()+sc.nextLine());
		   return error;
	   } catch (FileNotFoundException e) {
		   // No debería fallar esto.
	   }
	   return "NOFILE";
	   
   }
	
   public void errorLexico(int fila, String lexema) {
     System.out.println("ERROR lexico fila "+fila+": Caracter inexperado: "+lexema);
     System.out.println(sacarContexto(fila));
     System.exit(1);
   }  
   public void errorSintactico(UnidadLexica unidadLexica) {
     System.out.print("ERROR sintactico fila "+unidadLexica.fila()+": Elemento inexperado "+unidadLexica.value);
     System.out.println(sacarContexto(unidadLexica.fila()));
     System.exit(1);
   }
   public static void errorTipos(int fila, String tipo){
	 System.out.println("ERROR de tipos fila "+fila+": "+tipo);
     System.out.println(sacarContexto(fila));
	 System.exit(1);
   }
   
   private static String archivo;
}
