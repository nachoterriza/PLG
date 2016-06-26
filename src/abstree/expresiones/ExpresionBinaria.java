package src.abstree.expresiones;

import src.resolid.Visitante;
import src.errors.UnsuportedOperation;

public abstract class ExpresionBinaria extends Expresion{
	public ExpresionBinaria(Expresion op1, Expresion op2){
		this.op1 = op1;
		this.op2 = op2;
		this.fila = op1.getFila();
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
		v.previsit(this);
		op1.accept(v);
		
		op2.accept(v);
		v.postvisit(this);
		
	}
	
	protected Expresion op1;
	protected Expresion op2;
}
