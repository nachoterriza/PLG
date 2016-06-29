package abstree.sentencias;

import java.util.LinkedList;

import errors.GestionErroresTiny;
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
	
	public int getFila() {
		return fila;
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

	public boolean checkTipo() {
		boolean ret = true;
		for(int i=0;i<entrada.size();i++) {
			Declaracion dec = ref.getEntrada().get(i);
			try {
				if(dec.getTipo().valorT()!=entrada.get(i).getTipo().valorT())
					throw new UnsuportedOperation("Parametro "+i+" de entrada mal declarado en CALL.");
			} catch (UnsuportedOperation e) {
				GestionErroresTiny.errorTipos(fila, e.getMessage());
				ret = false;
			}
			
		}
		for(int i=0;i<salida.size();i++) {
			Declaracion dec = ref.getSalida().get(i);			
			try {
				if(dec.getTipo().valorT()!=salida.get(i).getTipo().valorT())
					throw new UnsuportedOperation("Parametro "+i+" de salida mal declarado en CALL.");
			} catch (UnsuportedOperation e) {
				GestionErroresTiny.errorTipos(fila, e.getMessage());
				ret = false;
			}
			
		}
		return ret;
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
