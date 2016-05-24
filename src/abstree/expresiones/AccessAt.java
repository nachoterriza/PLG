package abstree.expresiones;


public class AccessAt extends ExpresionBinaria {

	public AccessAt(Expresion op1, Expresion op2) {
		super(op1, op2);
	}

	@Override
	public TipoE tipo() {
		return TipoE.AT;
	}

}
