package abstree;

import java.util.LinkedList;

import resolid.Anfitrion;
import resolid.Visitante;

public class Funcion implements Anfitrion{
	
	public Funcion(String id, LinkedList<Declaracion> entrada, 
			LinkedList<Declaracion> salida, Programa programa) {
		this.id = id;
		this.entrada = entrada;
		this.salida = salida;
		this.programa = programa;
	}

	public String getId() {
		return id;
	}
	
	@Override
	public void accept(Visitante v) {
		v.previsit(this);
		v.visit(id);
		for(Declaracion d: entrada)
			d.accept(v);
		for(Declaracion d: salida)
			d.accept(v);
		programa.accept(v);
		v.postvisit(this);
	}

	private String id;
	private LinkedList<Declaracion> entrada;
	private LinkedList<Declaracion> salida;
	private Programa programa;
}
