package abstree;

import java.util.LinkedList;

public class Codigo {

	public Codigo(Programa main, LinkedList<Funcion> funciones){
		this.main = main;
		this.funciones = funciones;
	}
	
	private Programa main;
	private LinkedList<Funcion> funciones;
}
