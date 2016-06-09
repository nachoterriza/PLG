package code;

import java.util.LinkedList;
import java.util.Stack;

import errors.CompilingException;
import errors.UnsuportedOperation;
import resolid.Visitante;
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

public class CodeVisitor implements Visitante {
	
	private CodeStack codeStack;
	
	public CodeVisitor(){
		this.codeStack = new CodeStack();
	}
	
	public LinkedList<String> getResult() throws CompilingException{
		if(codeStack.getNumBlocksStack() == 1){
			LinkedList<String> code =  IR.relToAbsJumps(codeStack.popCodeC());
			code.add(IR.stop());
			return code;
		}
			
		else
			throw new CompilingException("Se esperaba 1 bloque de codigo en la pila. "
					+ "Sin embargo hay" + codeStack.getNumBlocksStack());
	}
	
	@Override
	public void previsit(Codigo node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void previsit(Declaracion node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void previsit(Funcion node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postvisit(Codigo node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postvisit(Declaracion node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postvisit(Funcion node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void previsit(ArrayWithKeys node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void previsit(AllTo node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postvisit(Programa node) {}

	@Override
	public void postvisit(ExpresionBinaria node) {
		try {
			if (node instanceof AccessAt){
				// TODO Auto-generated method stub
			}
			else {
				LinkedList<String> rigth = codeStack.popCodeR();
				LinkedList<String> left = codeStack.popCodeR();
				left.addAll(rigth);
				left.add(IR.binary(node.tipo()));
				codeStack.pushCodeR(left);
			}
		} catch (CompilingException e){
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
	public void postvisit(ArrayWithKeys node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postvisit(AllTo node) {
		// TODO Auto-generated method stub

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
			code.add(IR.ldcInt( -666/*TODO hacer RO*/));
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
	public void previsit(Asignacion node) {}

	@Override
	public void previsit(Call node) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean previsit(Choose node) {

		LinkedList<String> caso;
		LinkedList<String> codechoose = new LinkedList<String>();
		int n;
		try{
			n = node.codeNum();
		} catch (UnsuportedOperation e) {
			e.printStackTrace();
			return false;
		}
		for(int i=n;i>=0;i--){
			try {
				//Apila el codigo del caso i. Si no hay caso i, salta al catch
				node.codeAt(i).accept(this); 
				caso = codeStack.popCodeC();
				//salto al final (tras el codigo del case i
				caso.add(IR.uncondj(codechoose.size())+i+1);
				caso.addAll(codechoose);
				//salto al codigo del case i (entrada de la tabla)
				caso.add(IR.uncondj(-caso.size()));
				codechoose = caso;
			} catch (UnsuportedOperation e) {
				//salto al final (entrada de la tabla)
				codechoose.add(IR.uncondj(i+1)); 
			} catch (CompilingException e) {
				e.printStackTrace();
			}
			
		}
		
		return false;
	}

	@Override
	public void previsit(While node) {
		// TODO Auto-generated method stub

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
	public void postvisit(Call node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postvisit(Choose node) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(int key) {
		// TODO Auto-generated method stub

	}

	@Override
	public void previsit(ArrayOf arrayOf) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postvisit(ArrayOf arrayOf) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Bool bool) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Int int1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void previsit(ExpresionBinaria node) {}

	@Override
	public void previsit(ExpresionUnaria node) {}

	@Override
	public void previsit(IfThenElse node) {}

	@Override
	public void previsit(Programa node) {}

}
