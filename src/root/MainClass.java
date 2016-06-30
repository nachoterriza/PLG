package root;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.LinkedList;

import java_cup.runtime.Symbol;
import resolid.PrettyPrinter;
import resolid.ResolID;
import abstree.Codigo;
import alex.AnalizadorLexicoTiny;
import asint.AnalizadorSintacticoTiny;
import code.CodeVisitor;
import code.RoVisitor;
import errors.CompilingException;
import errors.GestionErroresTiny;

public class MainClass {
	
	private static final String file = "ordenar"; 

	
	public static void main(String[] args) throws Exception {
		System.out.println("Iniciando proceso de compilacion de "+file+".code...");
		Reader input = new InputStreamReader(new FileInputStream(file+".code"));
		GestionErroresTiny.setArchivo(file+".code");
		AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
		AnalizadorSintacticoTiny asint = new AnalizadorSintacticoTiny(alex);
		asint.setScanner(alex);
		System.out.println("Iniciando analisis lexico-sintactico...");
		Symbol s;
		try {
			 s = asint.parse();
		} catch (Exception e) {
			System.out.println("Error en analisis lexico-sintactico");
			System.out.println("Abortando compilacion");
			return;
		}
		System.out.println("Analisis lexico-sintactico completado");
		
		Codigo codetree = (Codigo) s.value;
		ResolID resolid = new ResolID();
		System.out.println("Iniciando resolucion de identificadores...");
		codetree.accept(resolid);
		if(!GestionErroresTiny.ok()){
			System.out.println("Error en resolucion de identificadores");
			System.out.println("Abortando compilacion");
			return;
		}
		System.out.println("Resolucion de identificadores completada");
		
		System.out.println("Iniciando comprobacion de tipos...");
		codetree.checkTipo();
		if(!GestionErroresTiny.ok()){
			System.out.println("Error en comprobacion de tipos");
			System.out.println("Abortando compilacion");
			return;
		}
		System.out.println("Comprobacion de tipos completado");
		
		System.out.println("Iniciando localizacion de variables (RO)...");
		RoVisitor ro = new RoVisitor();
		codetree.accept(ro);
		if(!GestionErroresTiny.ok()){
			System.out.println("Error en localizacion de variables");
			System.out.println("Abortando compilacion");
			return;
		}
		/*XXX TEST*/ System.out.print(ro);
		System.out.println("Localizacion de variables completada (RO)");
		
		System.out.println("Iniciando compilacion...");
		CodeVisitor compiler = new CodeVisitor(ro);
		codetree.accept(compiler);
		System.out.println("Compilacion completada");
		
		printCode(compiler);
	}
	
	private static void printCode(CodeVisitor compiler){
		LinkedList<String> newcode;
		PrintWriter cout,cout2;
		try {
			cout = new PrintWriter(file+".mach");
			cout2 = new PrintWriter(file+".idxm");
			newcode = compiler.getResult();
			int i=0;
			for(String instr: newcode){
				   cout.println(instr);
				   cout2.println(""+i+": "+instr);
				   i++;
			}
			cout.close();
			cout2.close();
			System.out.println("Codigo maquina en "+file+".mach");
			System.out.println("Codigo maquina indexado en "+file+".idxm");
		} catch (CompilingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static void printTree(Codigo codetree, boolean printDeclaraciones) {
		PrettyPrinter pp = new PrettyPrinter(printDeclaraciones);
		codetree.accept(pp);
	}
}
