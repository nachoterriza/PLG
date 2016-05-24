package abstree.expresiones;

public class True extends Expresion {

	public True(){}
	
	@Override
	public TipoE tipo() {
		return TipoE.TRUE;
	}

}
