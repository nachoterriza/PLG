package resolid;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Stack;

import errors.IdentifyingIdException;
import abstree.Declaracion;

public class TablaDeSimbolos {

	public TablaDeSimbolos(){
		this.ptBloques = new Stack<Hashtable<String,Declaracion>>();
		this.tpIds = new Hashtable<String,Stack<Declaracion>>();
	}
	
	public void abreBloque(){
		this.ptBloques.add(new Hashtable<String,Declaracion>());
	}
	
	public void cierraBloque(){
		Enumeration<String> idlist = ptBloques.peek().keys();
		String id;
		while(idlist.hasMoreElements()){
			id = idlist.nextElement();
			tpIds.get(id).pop();
		}
		ptBloques.pop();
	}
	
	public void insertaId(String id, Declaracion decl) throws IdentifyingIdException {
		if(ptBloques.peek().contains(id)){
			throw new IdentifyingIdException("Identificador "+ id 
					+ " ya declarado");
		}
		else {
			ptBloques.peek().put(id, decl);
			Stack<Declaracion> stack = tpIds.get(id);
			stack.push(decl);
			tpIds.put(id, stack);
		}			
	}
	
	public Declaracion buscaId(int id) throws IdentifyingIdException {
		if(tpIds.containsKey(id))
			return tpIds.get(id).peek();
		else 
			throw new IdentifyingIdException("Identificador "+ id 
					+ " no declarado");
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
}
