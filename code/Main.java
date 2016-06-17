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
     Reader input = new InputStreamReader(new FileInputStream("decl.code"));
	 AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
	 AnalizadorSintacticoTiny asint = new AnalizadorSintacticoTiny(alex);
	 asint.setScanner(alex);
	 Symbol s = asint.parse();	 
	 
	 
	 Codigo codetree = (Codigo) s.value;

	 ResolID resolid = new ResolID();
	 codetree.accept(resolid);
	 System.out.println(" - resolucion de identificadores completada - ");
	 codetree.checkTipo();
	 System.out.println(" - Comprobacion de tipos completada - ");
	 RoVisitor ro = new RoVisitor();
	 codetree.accept(ro);
	 System.out.println(" - localizacion de variables completada - ");
	 CodeVisitor compiler = new CodeVisitor(ro);
	 codetree.accept(compiler);
	 printCode(compiler);
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
		RoVisitor ro = new RoVisitor();
		ifelse.accept(ro);
		CodeVisitor compiler = new CodeVisitor(ro);
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
		RoVisitor ro = new RoVisitor();
		bucle.accept(ro);
		CodeVisitor compiler = new CodeVisitor(ro);
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
		Identificador x = new Identificador("x");
		try {
			x.setRef(new Declaracion(new Int(), "x", new Number(0)));
		} catch (UnsuportedOperation e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int N = 3;
		Asignacion[] asig = new Asignacion[N];
		LinkedList<Sentencia>[] code= new LinkedList[N];
		Programa[] prog = new Programa[N];
		Hashtable<Integer,Programa> casos = new Hashtable<Integer,Programa>();
		for (int i=0;i<N;i++){
			asig[i] = new Asignacion(x, new Number(i));
			code[i]= new LinkedList<Sentencia>();
			code[i].add(asig[i]);
			prog[i] = new Programa(new LinkedList<Declaracion>(), code[i]);
			casos.put(i*2, prog[i]);
		}
		
		
		Choose choose = new Choose(x, casos);
		RoVisitor ro = new RoVisitor();
		x.accept(ro);
		choose.accept(ro);
		CodeVisitor compiler = new CodeVisitor(ro);
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
		Expresion id = new Identificador("i");
		Expresion exp3 = new AccessAt(exp2, id);
		Expresion exp4 = new AccessAt(exp3, new Number(1));
		try {
			exp1.setRef(new Declaracion(
					new ArrayOf(5, new ArrayOf(7, new ArrayOf(2,new Int()))),
					"x", exp4
					)
				);
			id.setRef(new Declaracion(new Int(),"i", new Number(69)));
		} catch (UnsuportedOperation e) {
			e.printStackTrace();
		}
		
		RoVisitor ro = new RoVisitor();
		exp1.accept(ro);
		id.accept(ro);
		exp4.accept(ro);
		CodeVisitor compiler = new CodeVisitor(ro);
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
	
	private static void printCode(CodeVisitor compiler){
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
	
	public static void testDecl(){
		Declaracion x = new Declaracion(new Int(), "x", new Number(0));
		Declaracion y = new Declaracion(new Int(), "x", new Number(0));
		Declaracion z = new Declaracion(new Int(), "x", new Number(0));
		RoVisitor ro = new RoVisitor();
		x.accept(ro);
		y.accept(ro);
		z.accept(ro);
		System.out.println(ro.ro(x));
		System.out.println(ro.ro(y));
		System.out.println(ro.ro(z));
		
	}
   
   public static void main(String[] args){
	   try {
		testMain();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }

}   
   
