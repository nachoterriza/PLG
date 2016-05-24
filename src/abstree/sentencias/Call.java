package abstree.sentencias;

import java.util.LinkedList;

import abstree.expresiones.Expresion;
import errors.UnsuportedOperation;

public class Call extends Sentencia {

	public Call(String id, LinkedList<Expresion> entrada, 
			LinkedList<Expresion> salida) {
		this.id = id;
		this.entrada = entrada;
		this.salida = salida;
	}
	
	@Override
	public TipoS tipo() {
		return TipoS.CALL;
	}
	
	public String functionID() throws UnsuportedOperation {
		return id;
	}
	public LinkedList<Expresion> entrada() throws UnsuportedOperation {
		return entrada;
	}
	public LinkedList<Expresion> salida() throws UnsuportedOperation {
		return salida;
	}

	
	private String id;
	private LinkedList<Expresion> entrada;
	private LinkedList<Expresion> salida;

}
