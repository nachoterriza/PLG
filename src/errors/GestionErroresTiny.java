package errors;

import alex.UnidadLexica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GestionErroresTiny {
	
   public static void setArchivo(String nombre) {
	   archivo = nombre;
   }
   
   private static void sacarContexto(int fila) {
	   try{
		   Scanner sc = new Scanner(new File(archivo));
		   int c = 1;
		   while(c<fila-1) {
			   sc.nextLine();
			   c++;
		   }
		   System.out.println("En la siguiente seccion de codigo: ");
		   System.out.println("" + c + ":\t" + sc.nextLine());
		   c++;
		   System.out.println("" + c + ":\t" + sc.nextLine());
		   c++;
		   System.out.println("" + c + ":\t" + sc.nextLine());
	   } catch (FileNotFoundException e) {
		   System.err.println("Archivo de codigo no encontrado");
	   }	   
   }
	
   public void errorLexico(int fila, String lexema) {
     System.out.println("ERROR lexico fila "+fila+": Caracter inexperado: "+lexema);

     sacarContexto(fila);
     System.exit(1);
   }  
   public void errorSintactico(UnidadLexica unidadLexica) {
     System.out.println("ERROR sintactico fila "+unidadLexica.fila()+": Elemento inexperado "+unidadLexica.value);
     sacarContexto(unidadLexica.fila());
     System.exit(1);
   }
   public static void errorTipos(int fila, String tipo){
	 System.out.println("ERROR de tipos fila "+fila+": "+tipo);

     sacarContexto(fila);
	 System.exit(1);
   }
   public static void errorID(int fila, String tipo, int filaDecl){
		 System.out.println("ERROR de identificadores fila "+fila+": "+tipo);
	     sacarContexto(fila);
	     if (filaDecl>0){
	    	 System.out.println("Anterior declaracion "+filaDecl);
	    	 sacarContexto(filaDecl);
	     }
		 System.exit(1);
	}	
   
   private static String archivo;
}
