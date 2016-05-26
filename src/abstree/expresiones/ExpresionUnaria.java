package abstree.expresiones;

import resolid.Visitante;
import errors.UnsuportedOperation;

public abstract class ExpresionUnaria extends Expresion{
	public ExpresionUnaria(Expresion op1){
		this.op1 = op1;
	}
	
	@Override
	public Expresion op1() throws UnsuportedOperation {
		return op1;
	}
	
	@Override
	public void accept(Visitante v) {
		v.visit(this);	
		op1.accept(v);
	}
	
	public abstract String getOperator();
	
	protected Expresion op1;
}
