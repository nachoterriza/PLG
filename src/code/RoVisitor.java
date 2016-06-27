package code;

import java.util.Hashtable;
import java.util.Stack;

import resolid.VisitorHelper;
import abstree.Declaracion;
import abstree.Funcion;
import abstree.Programa;
import abstree.expresiones.Identificador;
import errors.UnsuportedOperation;

/**

 * Recorre el árbol, asignando direcciones de memoria a las variables
 * del programa. La organización de la memoria es la siguiente:
 * <ul>
 * <li> Las direcciones son relativas al marco de pila
 * <li> Las direcciones de 0 a 4 están reservadas
 * <li> Se asignan las  direcciones a las variables de entrada a 
 * partir de la 5, teniendo en cuenta los tamaños.
 * <li> A continuacion se asignan las variables de salida. Se deja una
 * palabra libre para la direccion de la variable de salida del procedimiento
 * llamante ( ro(declSalida)-1 ).
 * <li> Por último, se asignan las variables del programa de los distintos bloques,
 * donde dos bloques hermanos comparten memoria.
 * </ul>
 */
public class RoVisitor extends VisitorHelper{

	private Hashtable<Declaracion,Integer> ro;
	private Hashtable<Funcion,Integer> lparam;
	private int nextdir;
	private Stack<Integer> dirstack;
	
	public RoVisitor(){
		this.ro = new Hashtable<Declaracion,Integer>();
		this.nextdir = 5;
		this.dirstack = new Stack<Integer>();
	}
	
	/**
	 * Devuelve la dirección de memoria asociada a la declaracion
	 * de este identificador
	 * @param id Identificador de la variable
	 * @return Direccion de memoria
	 */
	public int ro(Identificador id){
		int ret = -666;
		try {
			ret = ro.get(id.ref());
		} catch (UnsuportedOperation e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * Devuelve la dirección de memoria asociada a esta declaracion
	 * @param id Declaracion de la variable
	 * @return Direccion de memoria
	 */
	public int ro(Declaracion id){
		Integer ret = ro.get(id);
		if (ret == null)
			return -666;
		else
			return ret;
	}
	
	/**
	 * Devuelve la longitud de la zona de parámetros de la funcion
	 * @param f Declaracion de la funcion
	 * @return Tamaño de los parámetros en memoria
	 */
	public int lparam(Funcion f){
		Integer ret = lparam.get(f);
		if (ret == null)
			return -333;
		else
			return ret;
	}

	@Override
	public void previsit(Declaracion node) {
		this.ro.put(node, this.nextdir);
		this.nextdir = this.nextdir + node.getTipo().tam();
	}

	@Override
	public boolean previsit(Funcion node) {
		this.nextdir = 5;
		
		for (Declaracion d: node.getEntrada()){
			this.ro.put(d, this.nextdir);
			this.nextdir = this.nextdir + d.getTipo().tam();
		}
		for (Declaracion d: node.getSalida()){
			this.ro.put(d, this.nextdir+1);
			this.nextdir = this.nextdir + d.getTipo().tam()+1;
		}
		this.lparam.put(node, this.nextdir-5);
		node.getPrograma().accept(this);
		
		this.nextdir = 5;
		return false;
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
