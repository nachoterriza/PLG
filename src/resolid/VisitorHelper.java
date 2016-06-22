package resolid;

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

/**
 * Implementación del visitante que deja todas las funciones
 * vacías. Permite no recargar las clases que necesitan implementar 
 * muy pocas funciones. Para los previsit que permiten cortar la exploracion,
 * esta implementación no la corta.
 * @see Visitante#previsit(Choose)
 */
public class VisitorHelper implements Visitante {

	@Override public void previsit(Codigo node) {}
	@Override public void previsit(Declaracion node) {}
	@Override public void previsit(Funcion node) {}
	@Override public void previsit(Programa node) {}
	@Override public void postvisit(Codigo node) {}
	@Override public void postvisit(Declaracion node) {}
	@Override public void postvisit(Funcion node) {}
	@Override public void postvisit(Programa node) {}
	@Override public void previsit(ExpresionBinaria node) {}
	@Override public void previsit(ExpresionUnaria node) {}
	@Override public void previsit(ArrayWithKeys node) {}
	@Override public void previsit(AllTo node) {}
	@Override public void postvisit(ExpresionBinaria node) {}
	@Override public void postvisit(ExpresionUnaria node) {}
	@Override public void postvisit(ArrayWithKeys node) {}
	@Override public void postvisit(AllTo node) {}
	@Override public void visit(True node) {}
	@Override public void visit(False node) {}
	@Override public void visit(Identificador node) {}
	@Override public void visit(Number node) {}
	@Override public void previsit(Asignacion node) {}
	@Override public void previsit(Call node) {}
	@Override public boolean previsit(Choose node) {return true;}
	@Override public void previsit(IfThenElse node) {}
	@Override public void previsit(While node) {}
	@Override public void postvisit(Asignacion node) {}
	@Override public void postvisit(Call node) {}
	@Override public void postvisit(Choose node) {}
	@Override public void postvisit(IfThenElse node) {}
	@Override public void postvisit(While node) {}
	@Override public void visit(String id) {}
	@Override public void visit(int key) {}
	@Override public void previsit(ArrayOf arrayOf) {}
	@Override public void postvisit(ArrayOf arrayOf) {}
	@Override public void visit(Bool bool) {}
	@Override public void visit(Int int1) {}
}
