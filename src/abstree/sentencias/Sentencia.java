package abstree.sentencias;

import java.util.LinkedList;

import resolid.Anfitrion;
import abstree.Programa;
import abstree.expresiones.Expresion;
import errors.UnsuportedOperation;

public abstract class Sentencia implements Anfitrion{
	
	public abstract TipoS tipo();
	/**Devuelve el lado izquierdo de la asignacion o del considering del choose*/
	public Expresion var() throws UnsuportedOperation {throw new UnsuportedOperation("var");}
	/**Devuelve la expresion de la derecha de la asignacion, o de la condicion del if o while*/
	public Expresion exp() throws UnsuportedOperation {throw new UnsuportedOperation("exp");}
	/**
	 * Devuelve el código del programa, ya sea para if, while o choose
	 * @param i 
	 * <ul>
	 * <li>0 = codigo del if y del while
	 * <li>1 = codigo del else
	 * <li>n = codigo de "value n do" del choose
	 * </ul>
	 * @return
	 * @throws UnsuportedOperation En caso de que la operacion no esté soportada o
	 * el parámetro no sea correcto
	 */
	public Programa codeAt(int i) throws UnsuportedOperation {throw new UnsuportedOperation("code "+i);}
	/**Devuelve el maximo valor de n en los casos del choose (0 es el mínimo siempre)*/
	public int codeNum() throws UnsuportedOperation {throw new UnsuportedOperation("codenum");}
	
	public String functionID() throws UnsuportedOperation {throw new UnsuportedOperation("functionID");}
	public LinkedList<Expresion> entrada() throws UnsuportedOperation {throw new UnsuportedOperation("entrada");}
	public LinkedList<Expresion> salida() throws UnsuportedOperation {throw new UnsuportedOperation("salida");}
}
