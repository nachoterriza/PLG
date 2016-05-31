package abstree.tipos;

import resolid.Visitante;

public class ArrayOf extends Tipo{

	public ArrayOf(int tam, Tipo tipoElem){
		this.tam = tam;
		this.tipoElem = tipoElem;
	}
	@Override
	public void accept(Visitante v) {
		v.previsit(this);
		v.visit(tam);
		tipoElem.accept(v);
		v.postvisit(this);
		
	}
	private int tam;
	private Tipo tipoElem;
}
