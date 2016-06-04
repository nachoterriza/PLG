package src.abstree.expresiones;

import src.abstree.tipos.Int;
import src.abstree.tipos.Tipo;
import src.errors.UnsuportedOperation;


public class Suma extends ExpresionBinaria {

	public Suma(Expresion op1, Expresion op2) {
		super(op1, op2);
	}

	@Override
	public TipoE tipo() {
		return TipoE.SUMA;
	}
	
	public Tipo getTipo() throws UnsuportedOperation {
		if(op1.getTipo().equalsT(new Int()) && op2.getTipo().equalsT(new Int()))
			return new Int();
		else throw new UnsuportedOperation("Suma con no enteros.");
	}


	@Override
	public String getOperator() {
		// TODO Auto-generated method stub
		return null;
	}

}
