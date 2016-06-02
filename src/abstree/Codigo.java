package src.abstree;

import java.util.LinkedList;

import src.resolid.Anfitrion;
import src.resolid.Visitante;

public class Codigo implements Anfitrion{

	public Codigo(Programa main, LinkedList<Funcion> funciones){
		this.main = main;
		this.funciones = funciones;
	}
	
	@Override
	public void accept(Visitante v) {
		v.previsit(this);
		for(Funcion f: funciones)
			f.accept(v);
		main.accept(v);
		v.postvisit(this);
	}

	private Programa main;
	private LinkedList<Funcion> funciones;
}
