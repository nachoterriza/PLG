package abstree.sentencias;

import java.util.LinkedList;

import errors.UnsuportedOperation;
import abstree.Declaracion;
import abstree.Funcion;
import abstree.expresiones.Expresion;
import resolid.Visitante;

public class Call extends Sentencia {

	public Call(String id, LinkedList<Expresion> entrada, 
			LinkedList<Expresion> salida, int fila) {
		this.id = id;
		this.entrada = entrada;
		this.salida = salida;
		this.fila = fila;
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
			
			try {
				
				if(dec.getTipo().valorT()!=params.get(i).getTipo().valorT())
					throw new UnsuportedOperation("Parámetros de entrada mal declarados en CALL.");
			} catch (UnsuportedOperation e) {
				e.printStackTrace();
			}
			
		}
		LinkedList<Expresion> vars = salida;
		for(int i=0;i<vars.size();i++) {
			Declaracion dec = vars.get(i).ref();
			
			try {
				if(dec.getTipo().valorT()!=vars.get(i).getTipo().valorT())
					throw new UnsuportedOperation("Parámetros de salida mal declarados en CALL.");
			} catch (UnsuportedOperation e) {
				e.printStackTrace();
			}
			
		}
		return true;
	}
	
	@Override
	public void accept(Visitante v) {
		boolean cont =  v.previsit(this);
		if (!cont) return;
		
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
	private int fila;

}
