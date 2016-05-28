package resolid;

import errors.UnsuportedOperation;
import abstree.Codigo;
import abstree.Declaracion;
import abstree.Funcion;
import abstree.Programa;
import abstree.expresiones.AllTo;
import abstree.expresiones.ArrayWithKeys;
import abstree.expresiones.ExpresionBinaria;
import abstree.expresiones.ExpresionUnaria;
import abstree.expresiones.False;
import abstree.expresiones.Identificador;
import abstree.expresiones.Number;
import abstree.expresiones.True;
import abstree.sentencias.Asignacion;
import abstree.sentencias.Call;
import abstree.sentencias.Choose;
import abstree.sentencias.IfThenElse;
import abstree.sentencias.While;
import abstree.tipos.ArrayOf;
import abstree.tipos.Bool;
import abstree.tipos.Int;

public class PrettyPrinter implements Visitante {
	
	private int depth;

	public PrettyPrinter(){
		this.depth = 0;
	}
	
	private void printDepth(){
		for(int i=1;i<depth;i++)
			System.out.print("|  ");
		System.out.print("|--");
	}

	@Override
	public void previsit(Codigo node) {
		printDepth();
		System.out.println("Codigo");
		depth++;		
	}

	@Override
	public void previsit(Declaracion node) {
		printDepth();
		System.out.println("Declaracion");
		depth++;
	}

	@Override
	public void previsit(Funcion node) {
		printDepth();
		System.out.println("Funcion");
		depth++;
	}

	@Override
	public void previsit(Programa node) {
		printDepth();
		System.out.println("Programa");
		depth++;
	}

	@Override
	public void postvisit(Codigo node) {
		depth--;
	}

	@Override
	public void postvisit(Declaracion node) {
		depth--;
	}

	@Override
	public void postvisit(Funcion node) {
		depth--;
	}

	@Override
	public void postvisit(Programa node) {
		depth--;
	}

	@Override
	public void previsit(ExpresionBinaria node) {
		printDepth();
		System.out.println(node.tipo());
		depth++;
	}

	@Override
	public void previsit(ExpresionUnaria node) {
		printDepth();
		System.out.println(node.tipo());
		depth++;
	}

	@Override
	public void previsit(ArrayWithKeys node) {
		printDepth();
		System.out.println("ArrayWithKeys");
		depth++;
	}

	@Override
	public void previsit(AllTo node) {
		printDepth();
		System.out.println("AllTo");
		depth++;
	}

	@Override
	public void postvisit(ExpresionBinaria node) {
		depth--;
	}

	@Override
	public void postvisit(ExpresionUnaria node) {
		depth--;
	}

	@Override
	public void postvisit(ArrayWithKeys node) {
		depth--;
	}

	@Override
	public void postvisit(AllTo node) {
		depth--;
	}

	@Override
	public void visit(True node) {
		printDepth();
		System.out.println("True");
	}

	@Override
	public void visit(False node) {
		printDepth();
		System.out.println("False");
	}

	@Override
	public void visit(Identificador node) {
		printDepth();
		try {
			System.out.println(node.id());
		} catch (UnsuportedOperation e) {
			e.printStackTrace();
		}
	}

	@Override
	public void visit(Number node) {
		printDepth();
		try {
			System.out.println(node.num());
		} catch (UnsuportedOperation e) {
			e.printStackTrace();
		}
	}

	@Override
	public void previsit(Asignacion node) {
		printDepth();
		System.out.println("Asignacion");
		depth++;
	}

	@Override
	public void previsit(Call node) {
		printDepth();
		System.out.println("Call");
		depth++;
	}

	@Override
	public void previsit(Choose node) {
		printDepth();
		System.out.println("Choose");
		depth++;
	}

	@Override
	public void previsit(IfThenElse node) {
		printDepth();
		System.out.println("IfThenElse");
		depth++;
	}

	@Override
	public void previsit(While node) {
		printDepth();
		System.out.println("While");
		depth++;
	}

	@Override
	public void postvisit(Asignacion node) {
		depth--;
	}

	@Override
	public void postvisit(Call node) {
		depth--;
	}

	@Override
	public void postvisit(Choose node) {
		depth--;
	}

	@Override
	public void postvisit(IfThenElse node) {
		depth--;
	}

	@Override
	public void postvisit(While node) {
		depth--;
	}

	@Override
	public void visit(String id) {
		printDepth();
		System.out.println(id);		
	}

	@Override
	public void visit(int key) {
		printDepth();
		System.out.println(key);		
	}

	@Override
	public void previsit(ArrayOf arrayOf) {
		printDepth();
		System.out.println("ArrayOf");
		depth++;
	}

	@Override
	public void postvisit(ArrayOf arrayOf) {
		depth--;		
	}

	@Override
	public void visit(Bool bool) {
		printDepth();
		System.out.println("Bool");
	}

	@Override
	public void visit(Int int1) {
		printDepth();
		System.out.println("Int");
	}

	
}
