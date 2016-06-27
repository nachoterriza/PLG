package abstree.expresiones;

import errors.UnsuportedOperation;
import resolid.Visitante;

public abstract class ExpresionUnaria extends Expresion{
	public ExpresionUnaria(Expresion op1){
		this.op1 = op1;
		this.fila = op1.fila;
	}
	
	@Override
	public Expresion op1() throws UnsuportedOperation {
		return op1;
	}
	
	@Override
	public void accept(Visitante v) {
		v.previsit(this);	
		op1.accept(v);
		v.postvisit(this);
	}
	
	protected Expresion op1;
}
