package abstree.expresiones;


public class Suma extends ExpresionBinaria {

	public Suma(Expresion op1, Expresion op2) {
		super(op1, op2);
	}

	@Override
	public TipoE tipo() {
		return TipoE.SUMA;
	}

}
