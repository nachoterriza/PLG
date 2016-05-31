package abstree.sentencias;

import java.util.LinkedList;

import resolid.Visitante;
import abstree.Funcion;
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
	
	public Funcion getRef() throws UnsuportedOperation {
		return ref;
	}
	public void setRef(Funcion ref) throws UnsuportedOperation {
		this.ref = ref;
	}

	
	@Override
	public void accept(Visitante v) {
		v.previsit(this);
		v.visit(id);
		for(Expresion e: entrada)
			e.accept(v);
		for(Expresion e: salida)
			e.accept(v);
		v.postvisit(this);
	}


	private String id;
	private LinkedList<Expresion> entrada;
	private LinkedList<Expresion> salida;
	private Funcion ref;

}
