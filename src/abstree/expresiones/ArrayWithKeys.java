package src.abstree.expresiones;

import java.util.LinkedList;

import src.resolid.Visitante;
import src.abstree.tipos.Tipo;
import src.errors.UnsuportedOperation;

public class ArrayWithKeys extends Expresion {

	public ArrayWithKeys(LinkedList<Expresion> array){
		this.array = array;
	}
	@Override
	public TipoE tipo() {
		return TipoE.ARRAY;
	}
	
	public Expresion elemAt(int i) throws UnsuportedOperation {
		try {
			return array.get(i);
		}catch(IndexOutOfBoundsException e){
			throw new UnsuportedOperation("elemAt" +i);
		}
	}
	public int num() throws UnsuportedOperation {
		return array.size();
	}
	
	private LinkedList<Expresion> array;
	


	@Override
	public void accept(Visitante v) {
		v.previsit(this);
		for(Expresion e:array)
			e.accept(v);
		v.postvisit(this);
	}

	public Tipo getTipo() throws UnsuportedOperation {
		return array.get(1).getTipo();

	}

}
