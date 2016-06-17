package abstree.expresiones;

import abstree.tipos.Bool;
import abstree.tipos.Tipo;
import errors.UnsuportedOperation;


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
}
