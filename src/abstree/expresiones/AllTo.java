package abstree.expresiones;

import resolid.Visitante;
import abstree.tipos.ArrayOf;
import abstree.tipos.Bool;
import abstree.tipos.Int;
import abstree.tipos.Tipo;
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
		if(op1.getTipo()==new Int())
			return new ArrayOf(num,new Int());
		else if (op1.getTipo()==new Bool())
			return new ArrayOf(num,new Bool());
		else throw new UnsuportedOperation("Configuracion de array con tipo erróneo.");
	}

	private Expresion op1;
	private int num;
	@Override
	public void accept(Visitante v) {
		// TODO Auto-generated method stub
		
	}

}
