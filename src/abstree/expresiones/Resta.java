package abstree.expresiones;


public class Resta extends ExpresionBinaria {

	public Resta(Expresion op1, Expresion op2) {
		super(op1, op2);
	}

	@Override
	public TipoE tipo() {
		return TipoE.RESTA;
	}

}
