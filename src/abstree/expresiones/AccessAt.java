package abstree.expresiones;

import abstree.tipos.ArrayOf;
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
		Int test = new Int();
		ArrayOf testArray = new ArrayOf(1,new Int(),op1.getFila());
		if(op2.getTipo().valorT()!=test.valorT())
			throw new UnsuportedOperation("Indice de array no entero.");
		else if (op1.getTipo().valorT()<testArray.valorT()) 
			throw new UnsuportedOperation("Array de tipo erroneo.");
		else	
			return ((ArrayOf) op1.getTipo()).getTipoElem();
		 
			
	}

}
