package abstree.expresiones;

import abstree.tipos.Bool;
import abstree.tipos.Int;
import abstree.tipos.Tipo;
import errors.UnsuportedOperation;

public class LowerThan extends ExpresionBinaria {

	public LowerThan(Expresion op1, Expresion op2) {
		super(op1, op2);
	}

	@Override
	public TipoE tipo() {
		return TipoE.LT;
	}

	public Tipo getTipo() throws UnsuportedOperation {
		Int test = new Int();
		if(op1.getTipo().valorT()==test.valorT() && op2.getTipo().valorT()==test.valorT())
			return new Bool();
		else throw new UnsuportedOperation("Igualdad con no enteros(<).");
	}
}
