package abstree.expresiones;

import abstree.tipos.Bool;
import abstree.tipos.Int;
import abstree.tipos.Tipo;
import errors.UnsuportedOperation;

public class LowerOrEqual extends ExpresionBinaria {

	public LowerOrEqual(Expresion op1, Expresion op2) {
		super(op1, op2);
	}

	@Override
	public TipoE tipo() {
		return TipoE.LE;
	}

	public Tipo getTipo() throws UnsuportedOperation {
		if(op1.getTipo()==new Int() && op2.getTipo()==new Int())
			return new Bool();
		else throw new UnsuportedOperation("Igualdad con no enteros(<=).");
	}
}
