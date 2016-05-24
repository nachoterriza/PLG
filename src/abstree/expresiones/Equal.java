package abstree.expresiones;

public class Equal extends ExpresionBinaria {

	public Equal(Expresion op1, Expresion op2) {
		super(op1, op2);
	}

	@Override
	public TipoE tipo() {
		return TipoE.EQ;
	}

}
