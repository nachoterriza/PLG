package abstree.expresiones;

import java.util.LinkedList;

import resolid.Visitante;
import abstree.tipos.Tipo;
import errors.UnsuportedOperation;

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
	
	public Tipo getTipo() {
		
	}
	@Override
	public void accept(Visitante v) {
		for(Expresion e:array)
			e.accept(v);
	}

}
