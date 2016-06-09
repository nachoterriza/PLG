package code;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Hashtable;
import java.util.LinkedList;

import java_cup.runtime.Symbol;
import resolid.ResolID;
import abstree.Codigo;
import abstree.Declaracion;
import abstree.Programa;
import abstree.expresiones.AccessAt;
import abstree.expresiones.And;
import abstree.expresiones.Expresion;
import abstree.expresiones.False;
import abstree.expresiones.Identificador;
import abstree.expresiones.LowerThan;
import abstree.expresiones.Mult;
import abstree.expresiones.Not;
import abstree.expresiones.Number;
import abstree.expresiones.Suma;
import abstree.expresiones.True;
import abstree.sentencias.Asignacion;
import abstree.sentencias.Choose;
import abstree.sentencias.IfThenElse;
import abstree.sentencias.Sentencia;
import abstree.sentencias.While;
import abstree.tipos.ArrayOf;
import abstree.tipos.Int;
import alex.AnalizadorLexicoTiny;
import asint.AnalizadorSintacticoTiny;
import errors.CompilingException;
import errors.UnsuportedOperation;

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
	
	public static void testWhile(){
		Asignacion asig = new Asignacion(new Identificador("x"), new Number(5));
		LinkedList<Sentencia> code= new LinkedList<Sentencia>();
		code.add(asig);
		Programa prog = new Programa(new LinkedList<Declaracion>(), code);
		
		Expresion exp = new And(
				new LowerThan(
						new Identificador("ID"), 
						new Suma(new Number(2), new Number(3))
						),
				new Not( new False() )
			);
		
		While bucle = new While(exp, prog);
		CodeVisitor compiler = new CodeVisitor();
		bucle.accept(compiler);
		
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
	
	public static void testChoose(){
		int N = 3;
		Asignacion[] asig = new Asignacion[N];
		LinkedList<Sentencia>[] code= new LinkedList[N];
		Programa[] prog = new Programa[N];
		Hashtable<Integer,Programa> casos = new Hashtable<Integer,Programa>();
		for (int i=0;i<N;i++){
			asig[i] = new Asignacion(new Identificador("x"), new Number(i));
			code[i]= new LinkedList<Sentencia>();
			code[i].add(asig[i]);
			prog[i] = new Programa(new LinkedList<Declaracion>(), code[i]);
			casos.put(i*2, prog[i]);
		}
		
		
		Choose choose = new Choose(new Identificador("x"), casos);
		CodeVisitor compiler = new CodeVisitor();
		choose.accept(compiler);
		
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
	
	public static void testAccessAt(){
		Expresion exp1 = new Identificador("x");
		Expresion exp2 = new AccessAt(exp1, new Number(3));
		Expresion exp3 = new AccessAt(exp2, new Identificador("i"));
		Expresion exp4 = new AccessAt(exp3, new Number(1));
		try {
			exp1.setRef(new Declaracion(
					new ArrayOf(5, new ArrayOf(7, new ArrayOf(2,new Int()))),
					"x", exp4
					)
				);
		} catch (UnsuportedOperation e) {
			e.printStackTrace();
		}
		
		CodeVisitor compiler = new CodeVisitor();
		exp4.accept(compiler);
		
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
	   testAccessAt();
   }

}   
   
