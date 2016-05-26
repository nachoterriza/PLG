package abstree;

import abstree.expresiones.Expresion;
import abstree.tipos.Tipo;

public class Declaracion implements Anfitrion{
	
	public Declaracion(Tipo tipo, String id, Expresion valor){
		this.tipo = tipo;
		this.id = id;
		this.valor = valor;
	}
	
	public Tipo getTipo() {
		return tipo;
	}

	public String getId() {
		return id;
	}

	private Tipo tipo;
	private String id;
	private Expresion valor;

}
