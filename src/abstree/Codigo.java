package abstree;

import java.util.LinkedList;

import resolid.Anfitrion;
import resolid.Visitante;

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
	
	public int nFunciones(){
		return this.funciones.size();
	}
	
	public boolean checkTipo() {
		boolean ret = true;
		
		for(int i=0;i<funciones.size();i++) {
			if(!funciones.get(i).checkTipo()) {
				ret = false;
			}
		}
		return main.checkTipo() && ret;
		
	}

	private Programa main;
	private LinkedList<Funcion> funciones;
}
