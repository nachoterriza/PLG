package abstree.expresiones;

import abstree.tipos.Int;
import abstree.tipos.Tipo;
import errors.UnsuportedOperation;


public class Div extends ExpresionBinaria {

	public Div(Expresion op1, Expresion op2) {
		super(op1, op2);
	}

	@Override
	public TipoE tipo() {
		return TipoE.DIV;
	}

	public Tipo getTipo() throws UnsuportedOperation {
		Int test = new Int();
		if(op1.getTipo().valorT()==test.valorT() && op2.getTipo().valorT()==test.valorT())
			return new Int();
		else throw new UnsuportedOperation("Division con no enteros.");
	}
}
