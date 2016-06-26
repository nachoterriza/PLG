package src.resolid;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Stack;

import src.abstree.Declaracion;
import src.abstree.Funcion;
import src.errors.IdentifyingIdException;

public class TablaDeSimbolos {

	public TablaDeSimbolos(){
		this.ptBloques = new Stack<Hashtable<String,Declaracion>>();
		this.tpIds = new Hashtable<String,Stack<Declaracion>>();
		this.tFunciones = new Hashtable<String,Funcion>();
	}
	
	public void abreBloqueV(){
		this.ptBloques.add(new Hashtable<String,Declaracion>());
	}
	
	public void cierraBloqueV(){
		Enumeration<String> idlist = ptBloques.peek().keys();
		String id;
		while(idlist.hasMoreElements()){
			id = idlist.nextElement();
			tpIds.get(id).pop();
		}
		ptBloques.pop();
	}
	
	public void insertaIdV(String id, Declaracion decl) throws IdentifyingIdException {
		if(ptBloques.peek().containsKey(id)){
			throw new IdentifyingIdException("Variable "+ id 
					+ " ya declarada");
		}
		else {
			ptBloques.peek().put(id, decl);
			Stack<Declaracion> stack = tpIds.get(id);
			if (stack == null)
				stack = new Stack<Declaracion>();
			stack.push(decl);
			tpIds.put(id, stack);
		}			
	}
	
	public void insertaIdF(String id, Funcion decl) throws IdentifyingIdException {
		if(tFunciones.contains(id)){
			throw new IdentifyingIdException("Funcion "+ id 
					+ " ya declarada");
		}
		else {
			tFunciones.put(id, decl);
		}			
	}
	
	public Declaracion buscaIdV(String id) throws IdentifyingIdException {
		if(tpIds.containsKey(id))
			return tpIds.get(id).peek();
		else 
			throw new IdentifyingIdException("Variable "+ id 
					+ " no declarada");
	}
	
	public Funcion buscaIdF(String id) throws IdentifyingIdException {
		if(tFunciones.containsKey(id))
			return tFunciones.get(id);
		else 
			throw new IdentifyingIdException("Funcion "+ id 
					+ " no declarada");
	}
	
	/**Pila de tablas <id,referencia al arbol abstracto>.
	 * Cada tabla representa un bloque anidado del programa
	 */
	private Stack<Hashtable<String,Declaracion>> ptBloques;
	/** Tabla <id, pila de referencias al arbol>.
	 * por cada entrada se almacenan las declaraciones
	 * de ese identificador el todos los bloques anidados,
	 * siendo la cima el bloque mas interno
	 */
	private Hashtable<String,Stack<Declaracion>> tpIds;
	
	/** Tabla de <id, Funcion>. Al estar todas las funciones en el nivel
	 * 0, no es necesario crear una tabla de plias o una plia de tablas.
	 * Adem�s, las declaraciones de funciones no se pueden mezclar con las 
	 * de variables (las funciones siempre est�n en el bloque 0 y las
	 * variables del 1 en adelante. Se podr�a integrar esta tabla en las otras
	 * pero evitamos as� mezclar ambas cosas
	 * 
	 */
	private Hashtable<String,Funcion> tFunciones;
}
