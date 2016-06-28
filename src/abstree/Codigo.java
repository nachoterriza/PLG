package abstree;

import java.util.LinkedList;

import errors.UnsuportedOperation;
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
	
	public boolean checkTipo() throws UnsuportedOperation {
		LinkedList<Funcion> funcs = funciones;
		if(main.checkTipo()){
			for(int i=0;i<funcs.size();i++) {
				if(!funcs.get(i).checkTipo())
					throw new UnsuportedOperation("Error de tipos en el codigo (funciones).");
			}
		return true;
		}
		throw new UnsuportedOperation("Error de tipos en el codigo (main).");
	}

	private Programa main;
	private LinkedList<Funcion> funciones;
}
