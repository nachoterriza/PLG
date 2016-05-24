package abstree.expresiones;


public class Not extends ExpresionUnaria {

	public Not(Expresion op1) {
		super(op1);
	}

	@Override
	public TipoE tipo() {
		return TipoE.NOT;
	}
	
	public Tipo getTipo() throws UnsuportedOperation {
		if(op1.getTipo()==new Bool())
			return new Bool();
		else throw new UnsuportedOperation("Not sin booleanos.");
	}

}
