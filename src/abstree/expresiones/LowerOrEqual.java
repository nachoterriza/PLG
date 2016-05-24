package abstree.expresiones;

public class LowerOrEqual extends ExpresionBinaria {

	public LowerOrEqual(Expresion op1, Expresion op2) {
		super(op1, op2);
	}

	@Override
	public TipoE tipo() {
		return TipoE.LE;
	}

}
