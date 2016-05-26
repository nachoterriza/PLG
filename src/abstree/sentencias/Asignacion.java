package abstree.sentencias;

import resolid.Visitante;
import abstree.expresiones.Expresion;
import errors.UnsuportedOperation;

public class Asignacion extends Sentencia {

	public Asignacion(Expresion varleft, Expresion expright){
		this.varleft= varleft;
		this.expright = expright;
	}
	@Override
	public TipoS tipo() {
		return TipoS.ASIGN;
	}
	
	public Expresion var() throws UnsuportedOperation {
		return varleft;
	}
	
	public Expresion exp() throws UnsuportedOperation {
		return expright;
	}
	
	private Expresion varleft;
	private Expresion expright;
	
	@Override
	public void accept(Visitante v) {
		varleft.accept(v);
		v.visit(this);
		expright.accept(v);
	}
}
