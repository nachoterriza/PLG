package abstree.expresiones;

import resolid.Visitante;
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
	
	@Override
	public void accept(Visitante v) {
		op1.accept(v);
		v.visit(this);
		op2.accept(v);
		
	}
	
	//public abstract String getOperator();
	public  String getOperator(){
		return tipo().toString();
	}
	
	protected Expresion op1;
	protected Expresion op2;
}
