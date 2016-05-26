package abstree;

import java.util.LinkedList;

import abstree.sentencias.Sentencia;

public class Programa {
	
	public Programa(LinkedList<Declaracion> declaraciones, LinkedList<Sentencia> sentencias){
		this.declaraciones = declaraciones;
		this.sentencias = sentencias;
	}
	
	public checkTipo() throws UnsuportedOperation {
		LinkedList<Declaracion> decs = declaraciones;
		for(int i=1;i<=decs.size();i++) {
			if(!decs.get(i).checkTipo())
				throw new UnsuportedOperation("Error en los tipos de las declaraciones del programa.")
		}
		LinkedList<Sentencia> sents = sentencias;
		for(int i=1;i<=sents.size();i++) {
			if(!sents.get(i).checkTipo())
				throw new UnsuportedOperation("Error en los tipos de las sentencias del programa.")
		}
		
		return true;
	}
	
	private LinkedList<Declaracion> declaraciones;
	private LinkedList<Sentencia> sentencias;
}
