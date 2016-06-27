package abstree.expresiones;

import errors.UnsuportedOperation;
import abstree.tipos.Bool;
import abstree.tipos.Int;
import abstree.tipos.Tipo;


public class GreaterThan extends ExpresionBinaria {

	public GreaterThan(Expresion op1, Expresion op2) {
		super(op1, op2);
	}

	@Override
	public TipoE tipo() {
		return TipoE.GT;
	}

	public Tipo getTipo() throws UnsuportedOperation {
		Int test = new Int();
		if(op1.getTipo().valorT()==test.valorT() && op2.getTipo().valorT()==test.valorT())
			return new Bool();
		else throw new UnsuportedOperation("Desigualdad con no enteros(>).");
	}
}
