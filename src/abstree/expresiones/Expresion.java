package abstree.expresiones;

import resolid.Anfitrion;
import abstree.Declaracion;
import abstree.tipos.Tipo;
import errors.UnsuportedOperation;

public abstract class Expresion implements Anfitrion{
	public abstract TipoE tipo();
	public Expresion op1() throws UnsuportedOperation {throw new UnsuportedOperation("op1");}
	public Expresion op2() throws UnsuportedOperation {throw new UnsuportedOperation("op2");}
	public int num() throws UnsuportedOperation {throw new UnsuportedOperation("num");}
	public String id() throws UnsuportedOperation {throw new UnsuportedOperation("id");}
	/**Devuelve la referencia a la declaracion de la variable*/
	public Declaracion ref() throws UnsuportedOperation {throw new UnsuportedOperation("ref");}
	public void setRef(Declaracion ref) throws UnsuportedOperation {throw new UnsuportedOperation("setRef");}
	public Expresion elemAt(int i) throws UnsuportedOperation {throw new UnsuportedOperation("elemAt" +i);}
	public abstract Tipo getTipo() throws UnsuportedOperation;
}
