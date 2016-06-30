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
		   sc.close();
	   } catch (FileNotFoundException e) {
		   System.err.println("Archivo de codigo no encontrado");
	   }	   
   }
	
   public void errorLexico(int fila, String lexema) {
	   ok = false;
     System.err.println("ERROR lexico fila "+fila+": Caracter inexperado: "+lexema);

     sacarContexto(fila);
   }  
   public void errorSintactico(UnidadLexica unidadLexica) {
	   ok = false;
     System.err.println("ERROR sintactico fila "+unidadLexica.fila()+": Elemento inexperado "+unidadLexica.value);
     sacarContexto(unidadLexica.fila());
   }
   public static void errorTipos(int fila, String tipo){
	   ok = false;
	 System.err.println("ERROR de tipos fila "+fila+": "+tipo);

     sacarContexto(fila);
   }
   public static void errorID(int fila, String tipo, int filaDecl){
	   ok = false;
		 System.err.println("ERROR de identificadores fila "+fila+": "+tipo);
	     sacarContexto(fila);
	     if (filaDecl>0){
	    	 System.out.println("Anterior declaracion: fila "+filaDecl);
	    	 sacarContexto(filaDecl);
	     }
	}	
   
   public static void errorRO(int fila, String tipo) {
	   ok = false;
		 System.err.println("ERROR de localizacion RO fila "+fila+": "+tipo);
	
}

public static boolean ok(){
	   return ok;
   }
   
   private static String archivo;
   private static boolean ok = true;
}
