package abstree.expresiones;

import errors.UnsuportedOperation;
import abstree.tipos.Int;
import abstree.tipos.Tipo;


public class Suma extends ExpresionBinaria {

	public Suma(Expresion op1, Expresion op2) {
		super(op1, op2);
		this.fila = op1.getFila();
	}

	@Override
	public TipoE tipo() {
		return TipoE.SUMA;
	}
	
	public Tipo getTipo() throws UnsuportedOperation {
		Int test = new Int();
		if(op1.getTipo().valorT()==test.valorT() && op2.getTipo().valorT()==test.valorT())
			return new Int();
		else throw new UnsuportedOperation("Suma con no enteros.");
	}

}
