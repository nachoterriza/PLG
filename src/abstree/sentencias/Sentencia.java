package src.abstree.sentencias;

import java.util.LinkedList;

import src.resolid.Anfitrion;
import src.abstree.Funcion;
import src.abstree.Programa;
import src.abstree.expresiones.Expresion;
import src.errors.UnsuportedOperation;

public abstract class Sentencia implements Anfitrion{
	
	public abstract TipoS tipo();
	/**Devuelve el lado izquierdo de la asignacion o del considering del choose*/
	public Expresion var() throws UnsuportedOperation {throw new UnsuportedOperation("var");}
	/**Devuelve la expresion de la derecha de la asignacion, o de la condicion del if o while*/
	public Expresion exp() throws UnsuportedOperation {throw new UnsuportedOperation("exp");}
	/**
	 * Devuelve el c�digo del programa, ya sea para if, while o choose
	 * @param i 
	 * <ul>
	 * <li>0 = codigo del if y del while
	 * <li>1 = codigo del else
	 * <li>n = codigo de "value n do" del choose
	 * </ul>
	 * @return
	 * @throws UnsuportedOperation En caso de que la operacion no est� soportada o
	 * el par�metro no sea correcto
	 */
	public Programa codeAt(int i) throws UnsuportedOperation {throw new UnsuportedOperation("code "+i);}
	/**Devuelve el maximo valor de n en los casos del choose (0 es el m�nimo siempre)*/
	public int codeNum() throws UnsuportedOperation {throw new UnsuportedOperation("codenum");}
	
	
	public Funcion getRef() throws UnsuportedOperation {throw new UnsuportedOperation("functionRef");}
	public void setRef(Funcion ref) throws UnsuportedOperation {throw new UnsuportedOperation("functionRef");}
	
	public String functionID() throws UnsuportedOperation {throw new UnsuportedOperation("functionID");}
	
	public boolean checkTipo() throws UnsuportedOperation {throw new UnsuportedOperation("Tipos incorrectos.");};
	
	public LinkedList<Expresion> entrada() throws UnsuportedOperation {throw new UnsuportedOperation("entrada");}
	public LinkedList<Expresion> salida() throws UnsuportedOperation {throw new UnsuportedOperation("salida");}
}
