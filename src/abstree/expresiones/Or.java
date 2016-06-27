package abstree.expresiones;

import errors.UnsuportedOperation;
import abstree.tipos.Bool;
import abstree.tipos.Tipo;


public class Or extends ExpresionBinaria {

	public Or(Expresion op1, Expresion op2) {
		super(op1, op2);
	}

	@Override
	public TipoE tipo() {
		return TipoE.OR;
	}
	
	public Tipo getTipo() throws UnsuportedOperation {
		Bool test = new Bool();
		if(op1.getTipo().valorT()==test.valorT() && op2.getTipo().valorT()==test.valorT())
			return new Bool();
		else throw new UnsuportedOperation("OR sin Booleanos.");
	}

}
