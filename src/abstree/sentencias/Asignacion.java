package abstree.sentencias;

import resolid.Visitante;
import abstree.expresiones.Expresion;
import errors.GestionErroresTiny;
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
		int valorL = varleft.getTipo().valorT();
		int valorR = expright.getTipo().valorT();
		
		try{
			if(valorR!=valorL)
				throw new UnsuportedOperation("Asignacion de tipos erroneos.");
		} catch (UnsuportedOperation e) {
			GestionErroresTiny.errorTipos(varleft.getFila(), e.getMessage());
			return false;
		}
		
		Expresion arrayL;
		Expresion arrayR;
		
		while(valorL>=2) {
			arrayL = varleft;
			arrayR = expright;
			
			try {
				if(arrayL.getTipo().numElems()!=arrayR.getTipo().numElems())
					throw new UnsuportedOperation("Asignacion entre arrays de distinto tamaño.");
			} catch (UnsuportedOperation e) {
				GestionErroresTiny.errorTipos(varleft.getFila(), e.getMessage());
				return false;
			}
			
			arrayL = arrayL.elemAt(0);
			arrayR = arrayR.elemAt(0);
			valorL-=2;
		}
		
		return true;
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
