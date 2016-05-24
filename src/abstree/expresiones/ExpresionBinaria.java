package abstree.expresiones;

import errors.UnsuportedOperation;

public abstract class ExpresionBinaria extends Expresion{
	public ExpresionBinaria(Expresion op1, Expresion op2){
		this.op1 = op1;
		this.op2 = op2;
	}
	
	@Override
	public Expresion op1() throws UnsuportedOperation {
		return op1;
	}
	
	@Override
	public Expresion op2() throws UnsuportedOperation {
		return op2;
	}
	
	private Expresion op1;
	private Expresion op2;
}
