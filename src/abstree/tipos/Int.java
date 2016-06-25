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

	@Override
	public int numElems() {
		return 0;
	}

	@Override
	public int tam() {
		return 1;
	}

	@Override
	public int dsuper() {
		return 0;
	}
}
