package abstree.expresiones;

import errors.UnsuportedOperation;
import abstree.tipos.Int;
import abstree.tipos.Tipo;


public class Resta extends ExpresionBinaria {

	public Resta(Expresion op1, Expresion op2) {
		super(op1, op2);
	}

	@Override
	public TipoE tipo() {
		return TipoE.RESTA;
	}
	
	public Tipo getTipo() throws UnsuportedOperation {
		Int test = new Int();
		if(op1.getTipo().valorT()==test.valorT() && op2.getTipo().valorT()==test.valorT())
			return new Int();
		else throw new UnsuportedOperation("Resta con no enteros.");
	}

}
