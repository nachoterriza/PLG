package code;

import java.util.Hashtable;
import java.util.LinkedList;

import errors.CompilingException;
import errors.UnsuportedOperation;

import resolid.Visitante;
import resolid.VisitorHelper;

import abstree.Codigo;
import abstree.Declaracion;
import abstree.Funcion;
import abstree.Programa;
import abstree.expresiones.AccessAt;
import abstree.expresiones.AllTo;
import abstree.expresiones.ArrayWithKeys;
import abstree.expresiones.Expresion;
import abstree.expresiones.ExpresionBinaria;
import abstree.expresiones.ExpresionUnaria;
import abstree.expresiones.False;
import abstree.expresiones.Identificador;
import abstree.expresiones.Not;
import abstree.expresiones.Number;
import abstree.expresiones.True;
import abstree.sentencias.Asignacion;
import abstree.sentencias.Call;
import abstree.sentencias.Choose;
import abstree.sentencias.IfThenElse;
import abstree.sentencias.TipoS;
import abstree.sentencias.While;
import abstree.tipos.ArrayOf;
import abstree.tipos.Bool;
import abstree.tipos.Int;
import resolid.Visitante;

/**
 * Visitante que en su recorrido por el arbol abstracto, 
 * compila el cï¿½digo representado por el cï¿½digo. Para ello,
 * el ï¿½rbol debe ejecutar {@link Codigo#accept(Visitante)} con
 * esta clase como parï¿½metro. <br><br>
 * 
 * Este visitante en las funciones visit() deberï¿½a apilar el codigo
 * correspondiente a los nodos hoja en la pila de codigo. En las funciones
 * postvisit(), deberï¿½a desapilar los cï¿½digos de sus hijos, combinarlos y 
 * apilar un ï¿½nico bloque de cï¿½digo correspondiente al subï¿½rbol que forma.
 * Las funciones previsit() apenas serï¿½n usadas, a menos que queramos cambiar
 * o controlar de alguna manera cï¿½mo se visitan los hijos. Este es el caso de 
 * {@link CodeVisitor#previsit(Choose)}.
 * 
 * <br> <br><b>--//--SIN TERMINAR DE IMPLEMENTAR--//--</b>
 * 
 * @see CodeStack
 */
public class CodeVisitor extends VisitorHelper {
	
	private Hashtable<Funcion,Integer> startDirTable;
	private int nextStartDir; 
	private CodeStack codeStack;
	private RoVisitor ro;
	
	/**
	 * Crea un nuevo CodeVisitor
	 * @param ro Funciï¿½n que obtiene las direcciones de las variables. <br>
	 * El ï¿½rbol debe ejecutar previamente {@link Codigo#accept(Visitante)} con
	 * el RoVisitor como parï¿½metro.
	 * @see RoVisitor
	 */
	public CodeVisitor(RoVisitor ro){
		this.codeStack = new CodeStack();
		this.ro = ro;
		this.startDirTable = new Hashtable<Funcion,Integer>();
		this.nextStartDir = 0;
	}
	
	/**
	 * Obtiene el cï¿½digo mï¿½quina resultante tras hacer {@link Codigo#accept(Visitante)} con
	 * esta clase como parï¿½metro. Si la compilaciï¿½n ha sido correcta, en la pila
	 * de cï¿½digo sï¿½lo deberï¿½a haber un bloque.
	 * @return
	 * @throws CompilingException
	 */
	public LinkedList<String> getResult() throws CompilingException{
		if(codeStack.getNumBlocksStack() == 1){
			LinkedList<String> code =  codeStack.popCodeC();
			code.add(IR.stop());
			return code;
		}
			
		else
			throw new CompilingException("Se esperaba 1 bloque de codigo en la pila. "
					+ "Sin embargo hay" + codeStack.getNumBlocksStack());
	}
	
	@Override
	public boolean previsit(Funcion node) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void postvisit(Funcion node) {
		LinkedList<String> code;
		try {
			code = this.codeStack.popCodeC();
			
			this.startDirTable.put(node, this.nextStartDir);
			this.nextStartDir = this.nextStartDir + code.size();
			IR.relToAbsJumps(code);
			
			this.codeStack.pushCodeC(code);
		} catch (CompilingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void previsit(Codigo node) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void postvisit(Codigo node) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void previsit(ArrayWithKeys node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postvisit(ArrayWithKeys node) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void previsit(AllTo node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postvisit(AllTo node) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void postvisit(Declaracion node) { //TODO cambiar asignacion para tipos compuestos
		try {
			LinkedList<String> rigth = codeStack.popCodeR();
			rigth.addFirst(IR.ldcAddr(ro.ro(node)));
			rigth.add(IR.sto());
			codeStack.pushCodeC(rigth);
		} catch (CompilingException e){
			e.printStackTrace();
		}
	}

	@Override
	public void postvisit(ExpresionBinaria node) {
		try {
			if (node instanceof AccessAt){
				LinkedList<String> rigth = codeStack.popCodeR();
				LinkedList<String> left = codeStack.popCodeL();
				
				left.addAll(rigth);
				left.add(IR.access(node.op1().getTipo().dsuper()));
				codeStack.pushCodeL(left);
			}
			else {
				LinkedList<String> rigth = codeStack.popCodeR();
				LinkedList<String> left = codeStack.popCodeR();
				left.addAll(rigth);
				left.add(IR.binary(node.tipo()));
				codeStack.pushCodeR(left);
			}
		} catch (CompilingException | UnsuportedOperation e){
			e.printStackTrace();
		}
	}

	@Override
	public void postvisit(ExpresionUnaria node) {
		try {
			if (!(node instanceof Not))
				throw new CompilingException("Expresion unaria no reconocida");
			LinkedList<String> code = codeStack.popCodeR();
			code.add(IR.not());
			codeStack.pushCodeR(code);
		} catch (CompilingException e){
			e.printStackTrace();
		}
	}

	@Override
	public void visit(True node) {
		try {
			LinkedList<String> code = new LinkedList<String>();
			code.add(IR.ldcTrue());
			codeStack.pushCodeR(code);
		} catch (CompilingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void visit(False node) {
		try {
			LinkedList<String> code = new LinkedList<String>();
			code.add(IR.ldcFalse());
			codeStack.pushCodeR(code);
		} catch (CompilingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void visit(Identificador node) {
		try {
			LinkedList<String> code = new LinkedList<String>();
			code.add(IR.ldcAddr(-666/*ro.ro(node)*/));
			codeStack.pushCodeL(code);
		} catch (CompilingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void visit(Number node) {
		try {
			LinkedList<String> code = new LinkedList<String>();
			code.add(IR.ldcInt(node.num()));
			codeStack.pushCodeR(code);
		} catch (CompilingException | UnsuportedOperation e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean previsit(Call node) {
		Funcion f;
		LinkedList<String> code;
		LinkedList<String> totalcode = new LinkedList<String>();
		totalcode.add(IR.startcall());
		
		try {
			f = node.getRef();

			for (Expresion e: node.entrada()){ //Tipo compuesto
				e.accept(this);
				code = this.codeStack.popCodeL();
				
				if (e.getTipo() instanceof ArrayOf) //Tipo compuesto
					code.add(IR.movs(e.getTipo().tam()));
				else  //Tipo simple
					code.add(IR.ind());
				
				totalcode.addAll(code); //añadimos el valor
				
			}
			for (Expresion e: node.salida()){
				e.accept(this);
				code = this.codeStack.popCodeL();
				totalcode.addAll(code); //Añadimos la referencia ...
				
				if (e.getTipo() instanceof ArrayOf) //Tipo compuesto
					code.add(IR.movs(e.getTipo().tam()));
				else  //Tipo simple
					code.add(IR.ind());
				
				totalcode.addAll(code);//.. y tambien añadimos el valor
			}
			
			totalcode.add(IR.callj(ro.lparam(f), startDirTable.get(f)));
			
		} catch (UnsuportedOperation e1) {
			e1.printStackTrace();
		} catch (CompilingException e1) {
			e1.printStackTrace();
		}
		return false;

	}

	@Override
	public boolean previsit(Choose node) {

		LinkedList<String> caso, varcode;
		LinkedList<String> codechoose = new LinkedList<String>();
		int n;
		try{
			n = node.codeNum();
		} catch (UnsuportedOperation e) {
			e.printStackTrace();
			return false;
		}
		
		//Cï¿½digo de los casos
		for(int i=n;i>=0;i--){
			try {
				//Apila el codigo del caso i. Si no hay caso i, salta al catch
				node.codeAt(i).accept(this); 
				caso = codeStack.popCodeC();
				//salto al final (tras el codigo del case i
				caso.add(IR.uncondj(codechoose.size()+i+2));
				caso.addAll(codechoose);
				//salto al codigo del case i (entrada de la tabla)
				caso.add(IR.uncondj(-caso.size()));
				codechoose = caso;
			} catch (UnsuportedOperation e) {
				//salto al final (entrada de la tabla)
				codechoose.add(IR.uncondj(i+1)); 
			} catch (CompilingException e) {
				e.printStackTrace();
				return false;
			}
			
		}
		
		//Cï¿½digo de la variable selectora
		try {
			node.var().accept(this);
			varcode = codeStack.popCodeR();
			varcode.add(IR.neg());
			varcode.add(IR.casej(codechoose.size()));
			varcode.addAll(codechoose);
			codeStack.pushCodeC(varcode);
		} catch (UnsuportedOperation | CompilingException e) {
			e.printStackTrace();
			return false;
		}
		
		return false;
	}

	@Override
	public void postvisit(Asignacion node) {
		try {
			LinkedList<String> rigth = codeStack.popCodeR();
			LinkedList<String> left = codeStack.popCodeL();
			left.addAll(rigth);
			left.add(IR.sto());
			codeStack.pushCodeC(left);
		} catch (CompilingException e){
			e.printStackTrace();
		}

	}

	@Override
	public void postvisit(IfThenElse node) {
		try {
			if (node.tipo() == TipoS.IF) {
				LinkedList<String> codeif = codeStack.popCodeC();
				LinkedList<String> cond = codeStack.popCodeR();
				int dirFin = codeif.size() +1; 
				String saltoFin = IR.condj(dirFin);
				cond.add(saltoFin);
				cond.addAll(codeif);
				codeStack.pushCodeC(cond);
			}
			else {
				LinkedList<String> codeelse = codeStack.popCodeC();
				LinkedList<String> codeif = codeStack.popCodeC();
				LinkedList<String> cond = codeStack.popCodeR();
				int dirElse = codeif.size() + 2; 
				String saltoElse = IR.condj(dirElse);
				int dirFin = codeelse.size() +1; 
				String saltoFin = IR.uncondj(dirFin);
				cond.add(saltoElse);
				cond.addAll(codeif);
				cond.add(saltoFin);
				cond.addAll(codeelse);
				codeStack.pushCodeC(cond);
			}
		} catch (CompilingException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void postvisit(While node) {
		try {
			LinkedList<String> code = codeStack.popCodeC();
			LinkedList<String> cond = codeStack.popCodeR();
			cond.add(IR.condj(code.size()+2));
			cond.addAll(code);
			cond.add(IR.uncondj(-cond.size()));
			codeStack.pushCodeC(cond);
		} catch (CompilingException e) {
			e.printStackTrace();
		}

	}

}
