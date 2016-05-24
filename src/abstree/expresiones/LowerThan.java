package abstree.expresiones;

public class LowerThan extends ExpresionBinaria {

	public LowerThan(Expresion op1, Expresion op2) {
		super(op1, op2);
	}

	@Override
	public TipoE tipo() {
		return TipoE.LT;
	}

}
