package src.abstree.tipos;

import src.resolid.Visitante;

public class Bool extends Tipo{
	public Bool(){}

	@Override
	public void accept(Visitante v) {
		v.visit(this);
	}
	
	public int valorT() {
		return 1;
	}
}
