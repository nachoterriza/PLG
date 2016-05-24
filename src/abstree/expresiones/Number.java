package abstree.expresiones;

import errors.UnsuportedOperation;

public class Number extends Expresion {

	public Number(int num){
		this.num = num;
	}
	
	@Override
	public int num() throws UnsuportedOperation {
		return num;
	}
	
	@Override
	public TipoE tipo() {
		return TipoE.NUM;
	}

	private int num;
}
