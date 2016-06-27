package abstree.expresiones;

import errors.UnsuportedOperation;
import abstree.tipos.Bool;
import abstree.tipos.Tipo;
import resolid.Visitante;

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
