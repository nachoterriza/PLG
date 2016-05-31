package abstree.expresiones;

import abstree.tipos.ArrayOf;
import abstree.tipos.Bool;
import abstree.tipos.Int;
import abstree.tipos.Tipo;
import errors.UnsuportedOperation;


public class AccessAt extends ExpresionBinaria {

	public AccessAt(Expresion op1, Expresion op2) {
		super(op1, op2);
	}

	@Override
	public TipoE tipo() {
		return TipoE.AT;
	}
	
	public Tipo getTipo() throws UnsuportedOperation {
		if(op2.getTipo()==new Int()) {
			if(op1.getTipo()==new ArrayOf(op2.num(),new Int()))
				return new Int();
			else if(op1.getTipo()==new ArrayOf(op2.num(),new Bool()))
				return new Bool();
			else 
				throw new UnsuportedOperation("Array de tipo erroneo.");
		}
		else 
			throw new UnsuportedOperation("Array de tipo erroneo.");
	}

}
