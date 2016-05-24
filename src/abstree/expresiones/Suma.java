package abstree.expresiones;


public class Suma extends ExpresionBinaria {

	public Suma(Expresion op1, Expresion op2) {
		super(op1, op2);
	}

	@Override
	public TipoE tipo() {
		return TipoE.SUMA;
	}
	
	public Tipo getTipo() throws UnsuportedOperation {
		if(op1.getTipo()==new Int() && op2.getTipo()==new Int())
			return new Int();
		else throw new UnsuportedOperation("Suma con no enteros.");
	}

}
