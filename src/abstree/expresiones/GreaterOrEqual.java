package abstree.expresiones;

public class GreaterOrEqual extends ExpresionBinaria {

	public GreaterOrEqual(Expresion op1, Expresion op2) {
		super(op1, op2);
	}

	@Override
	public TipoE tipo() {
		return TipoE.GE;
	}

}
