package abstree.expresiones;

import errors.UnsuportedOperation;

public abstract class ExpresionUnaria extends Expresion{
	public ExpresionUnaria(Expresion op1){
		this.op1 = op1;
	}
	
	@Override
	public Expresion op1() throws UnsuportedOperation {
		return op1;
	}
	
	private Expresion op1;
}
