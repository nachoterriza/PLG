package abstree.expresiones;

import abstree.tipos.Bool;
import abstree.tipos.Tipo;
import resolid.Visitante;
import errors.UnsuportedOperation;

public class True extends Expresion {

	public True(){}
	
	@Override
	public TipoE tipo() {
		return TipoE.TRUE;
	}
	
	public Tipo getTipo() throws UnsuportedOperation {
			return new Bool();
	}


	@Override
	public void accept(Visitante v) {
		v.visit(this);
	}

}
