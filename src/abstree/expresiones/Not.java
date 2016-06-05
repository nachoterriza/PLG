package src.abstree.expresiones;

import src.abstree.tipos.Bool;
import src.abstree.tipos.Tipo;
import src.errors.UnsuportedOperation;


public class Not extends ExpresionUnaria {

	public Not(Expresion op1) {
		super(op1);
	}

	@Override
	public TipoE tipo() {
		return TipoE.NOT;
	}
	
	public Tipo getTipo() throws UnsuportedOperation {
		Bool test = new Bool();
		if(op1.getTipo().valorT()==test.valorT())
			return new Bool();
		else throw new UnsuportedOperation("Not sin booleanos.");
	}


	@Override
	public String getOperator() {
		return "NOT";
	}


}
