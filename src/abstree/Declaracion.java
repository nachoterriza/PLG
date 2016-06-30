package abstree;

import errors.GestionErroresTiny;
import errors.UnsuportedOperation;
import abstree.expresiones.Expresion;
import abstree.tipos.Tipo;
import resolid.Anfitrion;
import resolid.Visitante;

public class Declaracion implements Anfitrion{
	

	public Declaracion(Tipo tipo, String id, Expresion valor, int fila){
		this.tipo = tipo;
		this.id = id;
		this.valor = valor;
		this.fila = fila;
	}
	
	public Tipo getTipo() {
		return tipo;
	}

	public String getId() {
		return id;
	}
	
	public String toString() {
		return id;
	}

	@Override
	public void accept(Visitante v) {
		v.previsit(this);
		tipo.accept(v);
		v.visit(id);
		if (valor != null)
			valor.accept(v);
		v.postvisit(this);
	}
	
	public boolean checkTipo() {
		try {
			if(valor.getTipo().valorT()==tipo.valorT())
				return true;
			else {
				GestionErroresTiny.errorTipos(fila, 
						"El tipo de la expresion no coincide con el tipo de la variable");
				return false;
			}
		} catch (UnsuportedOperation e) {
			GestionErroresTiny.errorTipos(fila, e.getLocalizedMessage());
			return false;
		}
	}
	
	public Expresion getValor() {
		return valor;
	}

	public int getFila() {
		return fila;
	}

	private Tipo tipo;
	private String id;
	private Expresion valor;
	private int fila;

}
