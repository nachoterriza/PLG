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
import abstree.expresiones.True;
import abstree.sentencias.Asignacion;
import abstree.sentencias.Call;
import abstree.sentencias.Choose;
import abstree.sentencias.IfThenElse;
import abstree.sentencias.While;


/** Esta clase permite aplicar operaciones sobre los nodos del
 * arbol abstracto, despreocupandose de su estructura.
 * sería el anfotión el que se preocupe de recorrer el arbol
 * mediante accept()
 */
public interface Visitante {
	void visit(Codigo node);
	void visit(Declaracion node);
	void visit(Funcion node);
	void visit(Programa node);
	
	void visit(ExpresionBinaria node);
	void visit(ExpresionUnaria node);
	
	void visit(ArrayWithKeys node);
	void visit(AllTo node);

	
	void visit(True node);
	void visit(False node);
	
	void visit(Identificador node);
	void visit(Number node);
	
	void visit(Asignacion node);
	void visit(Call node);
	void visit(Choose node);
	void visit(IfThenElse node);
	void visit(While node);
	}
