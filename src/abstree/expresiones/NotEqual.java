package abstree.expresiones;

public class NotEqual extends ExpresionBinaria{

	public NotEqual(Expresion op1, Expresion op2) {
		super(op1, op2);
	}

	@Override
	public TipoE tipo() {
		return TipoE.NEQ;
	}

}
