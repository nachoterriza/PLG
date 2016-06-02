package src.abstree.expresiones;

import src.resolid.Visitante;
import src.abstree.tipos.Bool;
import src.abstree.tipos.Tipo;
import src.errors.UnsuportedOperation;

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
