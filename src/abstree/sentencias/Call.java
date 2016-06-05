package abstree.sentencias;

import java.util.LinkedList;

import resolid.Visitante;
import abstree.Declaracion;
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

	public boolean checkTipo() throws UnsuportedOperation {
		LinkedList<Expresion> params = entrada;
		for(int i=0;i<params.size();i++) {
			Declaracion dec = params.get(i).ref();
			if(dec.getTipo()!=params.get(i).getTipo())
				throw new UnsuportedOperation("Parámetros de entrada mal declarados en CALL.");
			
		}
		LinkedList<Expresion> vars = salida;
		for(int i=0;i<vars.size();i++) {
			Declaracion dec = vars.get(i).ref();
			if(dec.getTipo()!=vars.get(i).getTipo())
				throw new UnsuportedOperation("Parámetros de salida mal declarados en CALL.");
			
		}
		return true;
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
