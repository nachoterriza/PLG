package abstree.expresiones;

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
	
	public TipoV getTipo() {
		return TipoV.ARRAYOF;
	}

	private Expresion op1;
	private int num;

}
