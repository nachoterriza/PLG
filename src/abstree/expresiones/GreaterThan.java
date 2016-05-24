package abstree.expresiones;


public class GreaterThan extends ExpresionBinaria {

	public GreaterThan(Expresion op1, Expresion op2) {
		super(op1, op2);
	}

	@Override
	public TipoE tipo() {
		return TipoE.GT;
	}

	public Tipo getTipo() throws UnsuportedOperation {
		if(op1.getTipo()==new Int() && op2.getTipo()==new Int())
			return new Bool();
		else throw new UnsuportedOperation("Desigualdad con no enteros(>).");
	}
}
