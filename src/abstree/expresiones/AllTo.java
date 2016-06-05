package abstree.expresiones;

import abstree.tipos.ArrayOf;
import abstree.tipos.Bool;
import abstree.tipos.Int;
import abstree.tipos.Tipo;
import resolid.Visitante;
import errors.UnsuportedOperation;

public class AllTo extends Expresion{

	public AllTo(Expresion op1, int num) {
		this.op1 = op1;
		this.num = num;
	}
	
	@Override
	public int num() throws UnsuportedOperation {
		return num;
	}
	

	@Override
	public Expresion op1() throws UnsuportedOperation {
		return op1;
	}
	
	@Override
	public TipoE tipo() {
		return TipoE.ALLTO;
	}
	
	public Tipo getTipo() throws UnsuportedOperation {
		Int test = new Int();
		Bool testB = new Bool();
		if(op1.getTipo().valorT()==test.valorT())
			return new ArrayOf(num,new Int());
		else if (op1.getTipo().valorT()==testB.valorT())
			return new ArrayOf(num,new Bool());
		else throw new UnsuportedOperation("Configuracion de array con tipo erróneo.");

	}

	@Override
	public void accept(Visitante v) {
		v.previsit(this);
		v.visit(num);
		op1.accept(v);
		v.postvisit(this);
	}

	private Expresion op1;
	private int num;

}
