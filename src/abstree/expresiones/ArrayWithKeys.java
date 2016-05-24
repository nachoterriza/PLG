package abstree.expresiones;

import java.util.LinkedList;

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

}
