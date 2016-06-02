package src.abstree.expresiones;

import src.abstree.tipos.Int;
import src.abstree.tipos.Tipo;
import src.errors.UnsuportedOperation;


public class Resta extends ExpresionBinaria {

	public Resta(Expresion op1, Expresion op2) {
		super(op1, op2);
	}

	@Override
	public TipoE tipo() {
		return TipoE.RESTA;
	}
	
	public Tipo getTipo() throws UnsuportedOperation {
		if(op1.getTipo()==new Int() && op2.getTipo()==new Int())
			return new Int();
		else throw new UnsuportedOperation("Resta con no enteros.");
	}

}
