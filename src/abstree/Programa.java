package abstree;

import java.util.LinkedList;

import abstree.sentencias.Sentencia;

public class Programa {
	
	public Programa(LinkedList<Declaracion> declaraciones, LinkedList<Sentencia> sentencias){
		this.declaraciones = declaraciones;
		this.sentencias = sentencias;
	}
	
	private LinkedList<Declaracion> declaraciones;
	private LinkedList<Sentencia> sentencias;
}
