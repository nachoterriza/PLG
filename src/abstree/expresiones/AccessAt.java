package src.abstree.expresiones;

import src.abstree.tipos.ArrayOf;
import src.abstree.tipos.Bool;
import src.abstree.tipos.Int;
import src.abstree.tipos.Tipo;
import src.errors.UnsuportedOperation;
import src.resolid.Visitante;


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
		ArrayOf testIntArray = new ArrayOf(1,new Int());
		ArrayOf testBoolArray = new ArrayOf(1,new Bool());
		if(op2.getTipo().valorT()==test.valorT()) {
			if(op1.getTipo().valorT()==testIntArray.valorT())
				return new Int();
			else if(op1.getTipo().valorT()==testBoolArray.valorT())
				return new Bool();
			else 
				throw new UnsuportedOperation("Array de tipo erroneo.");
		}
		else 
			throw new UnsuportedOperation("Array de tipo erroneo.");
	}

	@Override
	public void accept(Visitante v) {
		// TODO Auto-generated method stub
		
	}

}
