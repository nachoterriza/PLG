package abstree;

import abstree.expresiones.Expresion;
import abstree.tipos.Tipo;

public class Declaracion {
	
	public Declaracion(Tipo tipo, String id, Expresion valor){
		this.tipo = tipo;
		this.id = id;
		this.valor = valor;
	}

	private Tipo tipo;
	private String id;
	private Expresion valor;
}
