package abstree.expresiones;

import resolid.Visitante;
import abstree.tipos.Int;
import abstree.tipos.Tipo;
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
	
	public Tipo getTipo() throws UnsuportedOperation {
		return new Int();
	}

	@Override
	public void accept(Visitante v) {
		v.visit(this);
	}
}
