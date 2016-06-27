package src.resolid;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import abstree.Codigo;
import alex.AnalizadorLexicoTiny;
import asint.AnalizadorSintacticoTiny;
import java_cup.runtime.Symbol;

public class Main {
   public static void main(String[] args) throws Exception {
     Reader input = new InputStreamReader(new FileInputStream("maximo.code"));
	 AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
	 AnalizadorSintacticoTiny asint = new AnalizadorSintacticoTiny(alex);
	 asint.setScanner(alex);
	 Symbol s = asint.parse();	 
	 
	 
	 Codigo codetree = (Codigo) s.value;

	 ResolID resolid = new ResolID();
	 codetree.accept(resolid);
	 System.out.println(" - resolucion de identificadores completada - ");
	 codetree.checkTipo();
	// PrettyPrinter pp = new PrettyPrinter(false);
//	 codetree.accept(pp);
   }

}   
   
