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
	
	public boolean checkTipo() throws UnsuportedOperation {
		if(varleft.getTipo()==expright.getTipo())
			return true;
		else throw new UnsuportedOperation("Asignación errónea.");
	}
	
	private Expresion varleft;
	private Expresion expright;
	
	@Override
	public void accept(Visitante v) {
		v.previsit(this);
		varleft.accept(v);
		expright.accept(v);
		v.postvisit(this);
	}
}
