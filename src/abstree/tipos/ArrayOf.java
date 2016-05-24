package abstree.tipos;

public class ArrayOf extends Tipo{

	public ArrayOf(int tam, Tipo tipoElem){
		this.tam = tam;
		this.tipoElem = tipoElem;
	}
	private int tam;
	private Tipo tipoElem;
}
