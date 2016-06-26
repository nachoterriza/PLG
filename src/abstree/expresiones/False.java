package src.abstree.expresiones;

import src.abstree.tipos.Bool;
import src.abstree.tipos.Tipo;
import src.resolid.Visitante;
import src.errors.UnsuportedOperation;

public class False extends Expresion {

	public False(){}
	
	@Override
	public TipoE tipo() {
		return TipoE.FALSE;
	}

	public Tipo getTipo() throws UnsuportedOperation {
		return new Bool();
	}


	@Override
	public void accept(Visitante v) {
		v.visit(this);
	}
}
