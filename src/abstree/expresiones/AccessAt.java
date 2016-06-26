package src.abstree.expresiones;

import src.abstree.tipos.ArrayOf;
import src.abstree.tipos.Bool;
import src.abstree.tipos.Int;
import src.abstree.tipos.Tipo;
import src.resolid.Visitante;
import src.errors.UnsuportedOperation;


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
		if(op2.getTipo().valorT()==test.valorT()
				&& op1.getTipo().valorT()>=testArray.valorT()) 
			return ((ArrayOf) op1.getTipo()).getTipoElem();
		else 
			throw new UnsuportedOperation("Array de tipo erroneo.");
	}

}
