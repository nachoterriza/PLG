package abstree.expresiones;


public class Mult extends ExpresionBinaria {

	public Mult(Expresion op1, Expresion op2) {
		super(op1, op2);
	}

	@Override
	public TipoE tipo() {
		return TipoE.MULT;
	}
	
	public Tipo getTipo() throws UnsuportedOperation {
		if(op1.getTipo()==new Int() && op2.getTipo()==new Int())
			return new Int();
		else throw new UnsuportedOperation("Multiplicación con no enteros.");
	}

}
