package abstree.expresiones;

public class False extends Expresion {

	public False(){}
	
	@Override
	public TipoE tipo() {
		return TipoE.FALSE;
	}

}
