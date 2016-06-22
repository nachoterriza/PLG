package code;

import java.util.Hashtable;
import java.util.Stack;

import resolid.VisitorHelper;
import abstree.Codigo;
import abstree.Declaracion;
import abstree.Funcion;
import abstree.Programa;
import abstree.expresiones.Identificador;
import errors.UnsuportedOperation;

/**
 * Recorre el árbol, asignando direcciones de memoria a las variables
 * del programa. <br> <br><b>--//--SIN TERMINAR DE IMPLEMENTAR--//--</b>
 */
public class RoVisitor extends VisitorHelper{

	private Hashtable<Declaracion,Integer> ro;
	private int nextdir;
	private Stack<Integer> dirstack;
	
	public RoVisitor(){
		this.ro = new Hashtable<Declaracion,Integer>();
		this.nextdir = 5;
		this.dirstack = new Stack<Integer>();
	}
	
	public int ro(Identificador id){
		int ret = -666;
		try {
			ret = ro.get(id.ref());
		} catch (UnsuportedOperation e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public int ro(Declaracion id){
		int ret = -666;
		ret = ro.get(id);
		return ret;
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
	public void previsit(Declaracion node) {
		this.ro.put(node, this.nextdir);
		this.nextdir++;
	}

	@Override
	public void previsit(Funcion node) {
		this.nextdir = 5;
	}

	@Override
	public void postvisit(Funcion node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void previsit(Programa node) {
		this.dirstack.push(this.nextdir);
	}

	@Override
	public void postvisit(Programa node) {
		this.nextdir = this.dirstack.pop();
	}
}
