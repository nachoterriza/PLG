package src.abstree.expresiones;

import src.resolid.Visitante;
import src.abstree.tipos.Int;
import src.abstree.tipos.Tipo;
import src.errors.UnsuportedOperation;

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
