package abstree;

import java.util.LinkedList;

public class Funcion {
	
	public Funcion(String id, LinkedList<Declaracion> entrada, 
			LinkedList<Declaracion> salida, Programa programa) {
		this.id = id;
		this.entrada = entrada;
		this.salida = salida;
		this.programa = programa;
	}

	private String id;
	private LinkedList<Declaracion> entrada;
	private LinkedList<Declaracion> salida;
	private Programa programa;
}
