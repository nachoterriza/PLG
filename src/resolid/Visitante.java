package src.resolid;

import src.abstree.Codigo;
import src.abstree.Declaracion;
import src.abstree.Funcion;
import src.abstree.Programa;
import src.abstree.expresiones.AllTo;
import src.abstree.expresiones.ArrayWithKeys;
import src.abstree.expresiones.ExpresionBinaria;
import src.abstree.expresiones.ExpresionUnaria;
import src.abstree.expresiones.False;
import src.abstree.expresiones.Identificador;
import src.abstree.expresiones.True;
import src.abstree.sentencias.Asignacion;
import src.abstree.sentencias.Call;
import src.abstree.sentencias.Choose;
import src.abstree.sentencias.IfThenElse;
import src.abstree.sentencias.While;
import src.abstree.tipos.ArrayOf;
import src.abstree.tipos.Bool;
import src.abstree.tipos.Int;
import src.abstree.expresiones.Number;


/** Esta clase permite aplicar operaciones sobre los nodos del
 * arbol abstracto, despreocupandose de su estructura.
 * ser�a el anfitri�n el que se preocupe de recorrer el arbol
 * mediante accept()<br>
 * El arbol se recorre en profundidad, de manera que para cada nodo
 * <ul>
 * <li> Si es un nodo hoja, se hace un visit() del nodo
 * <li> Si es un nodo interno, se hace un previsit() del nodo,
 * se visitan todos sus hijos y despues se hace un postvisit() del nodo
 * </ul>
 */
public interface Visitante {
	
	void previsit(Codigo node);
	void previsit(Declaracion node);
	void previsit(Funcion node);
	void previsit(Programa node);
	
	void postvisit(Codigo node);
	void postvisit(Declaracion node);
	void postvisit(Funcion node);
	void postvisit(Programa node);
	
	void previsit(ExpresionBinaria node);
	void previsit(ExpresionUnaria node);
	void previsit(ArrayWithKeys node);
	void previsit(AllTo node);
	
	void postvisit(ExpresionBinaria node);
	void postvisit(ExpresionUnaria node);
	void postvisit(ArrayWithKeys node);
	void postvisit(AllTo node);
	
	void visit(True node);
	void visit(False node);
	
	void visit(Identificador node);
	void visit(Number node);
	
	//Sentencias
	void previsit(Asignacion node);
	void previsit(Call node);
	/**
	 * Visita este nodo. El valor de retorno permite cortar la exploraci�n del
	 * sub�rbol que cuelga del nodo. Esto permite, entre otras cosas, cambiar
	 * el orden o tipo de recorrido que se desea hacer sobre los hijos. El 
	 * valor de retorno cambia ligeramente la implementaci�n del accept() de este nodo.
	 * @param node Nodo a explorar
	 * @return <code>true</code> si se debe explorar el sub�rbol. <code>false</code>
	 * si se desea cortar la exploracion, ya sea para no explorar o redefinir la
	 * exploraci�n.
	 * @see Choose#accept(Visitante)
	 */
	boolean previsit(Choose node);
	void previsit(IfThenElse node);
	void previsit(While node);
	
	void postvisit(Asignacion node);
	void postvisit(Call node);
	void postvisit(Choose node);
	void postvisit(IfThenElse node);
	void postvisit(While node);
	
	void visit(String id);
	void visit(int key);
	
	void previsit(ArrayOf arrayOf);
	void postvisit(ArrayOf arrayOf);
	void visit(Bool bool);
	void visit(Int int1);
	
}
