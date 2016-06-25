package abstree.tipos;

import resolid.Visitante;

public class ArrayOf extends Tipo{

	public ArrayOf(int tam, Tipo tipoElem){
		this.tam = tam;
		this.tipoElem = tipoElem;
	}
	public int getTam() {
		return tam;
	}
	public Tipo getTipoElem() {
		return tipoElem;
	}
	@Override
	public void accept(Visitante v) {
		v.previsit(this);
		v.visit(tam);
		tipoElem.accept(v);
		v.postvisit(this);
		
	}
	
	public int valorT() {
		return 2+tipoElem.valorT();
	}
	
	@Override
	public int numElems() {
		return tam;
	}
	@Override
	public int tam() {
		return tam*tipoElem.tam();
	}
	@Override
	public int dsuper() {
		return tipoElem.tam();
	}

	private int tam;
	private Tipo tipoElem;
}
