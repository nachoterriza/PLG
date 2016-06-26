package src.abstree.tipos;

import src.resolid.Visitante;

public class ArrayOf extends Tipo{

	public ArrayOf(int tam, Tipo tipoElem, int fila){
		this.tam = tam;
		this.tipoElem = tipoElem;
		this.fila = fila;
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
	
	private int tam;
	private Tipo tipoElem;
	private int fila;
	
	@Override
	public int numElems() {
		// TODO Auto-generated method stub
		return tam;
	}
}
