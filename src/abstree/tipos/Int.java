package abstree.tipos;

import resolid.Visitante;

public class Int extends Tipo{
	
	public Int(){}
	
	@Override
	public void accept(Visitante v) {
		v.visit(this);
	}
	
	public int valorT() {
		return 0;
	}
}
