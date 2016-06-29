package abstree;

import java.util.LinkedList;

import resolid.Anfitrion;
import resolid.Visitante;
import abstree.sentencias.Sentencia;

public class Programa implements Anfitrion{
	
	public Programa(LinkedList<Declaracion> declaraciones, LinkedList<Sentencia> sentencias){
		this.declaraciones = declaraciones;
		this.sentencias = sentencias;
	}
	
	@Override
	public void accept(Visitante v) {
		v.previsit(this);
		for(Declaracion d: declaraciones)
			d.accept(v);
		for(Sentencia s: sentencias)
			s.accept(v);
		v.postvisit(this);		
	}


	public boolean checkTipo() {
		for(int i=0;i<declaraciones.size();i++) {
			if(!declaraciones.get(i).checkTipo())
				return false;
		}
		for(int i=0;i<sentencias.size();i++) {
			if(!sentencias.get(i).checkTipo())
				return false;
		}
		
		return true;
	}
	
	private LinkedList<Declaracion> declaraciones;
	private LinkedList<Sentencia> sentencias;
}
