package code;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.LinkedList;

import errors.CompilingException;
import java_cup.runtime.Symbol;
import resolid.ResolID;
import abstree.Codigo;
import abstree.Declaracion;
import abstree.Programa;
import abstree.expresiones.Identificador;
import abstree.expresiones.Number;
import abstree.expresiones.True;
import abstree.sentencias.Asignacion;
import abstree.sentencias.IfThenElse;
import abstree.sentencias.Sentencia;
import alex.AnalizadorLexicoTiny;
import asint.AnalizadorSintacticoTiny;

public class Main {
   public static void testMain() throws Exception {
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
   
   public static void testRelAbs() {
	   LinkedList<String> code = new LinkedList<String>();
	   code.add(IR.ldcInt(3));
	   code.add(IR.ldcInt(5));
	   code.add(IR.ind());
	   code.add(IR.uncondj(-1));
	   code.add(IR.condj(-1));
	   code.add(IR.add());
	   code.add(IR.sto());
	   
	   for(String instr: code)
		   System.out.println(instr);
	   
	   LinkedList<String> newcode = IR.relToAbsJumps(code);
	   
	   for(String instr: newcode)
		   System.out.println(instr);
   }
   
	public static void testIfElse(){
		Asignacion asig = new Asignacion(new Identificador("x"), new Number(5));
		LinkedList<Sentencia> code= new LinkedList<Sentencia>();
		code.add(asig);
		Programa prog = new Programa(new LinkedList<Declaracion>(), code);
		Asignacion asig2 = new Asignacion(new Identificador("y"), new Number(0));
		LinkedList<Sentencia> code2= new LinkedList<Sentencia>();
		code2.add(asig2);
		Programa prog2 = new Programa(new LinkedList<Declaracion>(), code2);
		
		IfThenElse ifelse = new IfThenElse(new True(), prog, prog2);
		CodeVisitor compiler = new CodeVisitor();
		ifelse.accept(compiler);
		
		LinkedList<String> newcode;
		try {
			newcode = compiler.getResult();
			int i=0;
			for(String instr: newcode){
				   System.out.println(""+i+": "+instr);
				   i++;
			}
		} catch (CompilingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
	   
	}
   
   public static void main(String[] args){
	   testIfElse();
   }

}   
   
