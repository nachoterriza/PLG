package abstree.expresiones;

import errors.UnsuportedOperation;
import abstree.tipos.Bool;
import abstree.tipos.Tipo;


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
