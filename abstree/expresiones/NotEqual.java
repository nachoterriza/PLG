package abstree.expresiones;

import abstree.tipos.Bool;
import abstree.tipos.Tipo;
import errors.UnsuportedOperation;

public class NotEqual extends ExpresionBinaria{

	public NotEqual(Expresion op1, Expresion op2) {
		super(op1, op2);
	}

	@Override
	public TipoE tipo() {
		return TipoE.NEQ;
	}
	
	public Tipo getTipo() throws UnsuportedOperation {
		Bool test = new Bool();
		if(op1.getTipo().valorT()==test.valorT())
			return new Bool();
		else throw new UnsuportedOperation("Negación sin Booleanos.");
	}

}
