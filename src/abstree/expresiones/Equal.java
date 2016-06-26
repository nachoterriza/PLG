package src.abstree.expresiones;

import src.abstree.tipos.Bool;
import src.abstree.tipos.Int;
import src.abstree.tipos.Tipo;
import src.errors.UnsuportedOperation;

public class Equal extends ExpresionBinaria {

	public Equal(Expresion op1, Expresion op2) {
		super(op1, op2);
	}

	@Override
	public TipoE tipo() {
		return TipoE.EQ;
	}
	
	public Tipo getTipo() throws UnsuportedOperation {
		Int test = new Int();
		if(op1.getTipo().valorT()==test.valorT() && op2.getTipo().valorT()==test.valorT())
			return new Bool();
		else throw new UnsuportedOperation("Igualdad con no enteros.");
	}

}
