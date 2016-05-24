package abstree.expresiones;


public class And extends ExpresionBinaria {

	public And(Expresion op1, Expresion op2) {
		super(op1, op2);
	}

	@Override
	public TipoE tipo() {
		return TipoE.AND;
	}
	
	public Tipo getTipo() throws UnsuportedOperation {
		if(op1.getTipo()==TipoV.BOOL && op2.getTipo()==TipoV.BOOL)
			return new Bool();
		else throw new UnsuportedOperation("Operacion AND entre tipos no Booleanos.");
	}

}
