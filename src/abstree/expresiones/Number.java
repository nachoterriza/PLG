package abstree.expresiones;

import errors.UnsuportedOperation;
import abstree.tipos.Int;
import abstree.tipos.Tipo;
import resolid.Visitante;

public class Number extends Expresion {

	public Number(int num){
		this.num = num;
	}
	
	public Number(int num, int fila){
		this.num = num;
		this.fila = fila;
	}
	
	@Override
	public int num() throws UnsuportedOperation {
		return num;
	}
	
	@Override
	public TipoE tipo() {
		return TipoE.NUM;
	}

	private int num;
	
	public Tipo getTipo() throws UnsuportedOperation {
		return new Int();
	}


	@Override
	public void accept(Visitante v) {
		v.visit(this);
	}

}
