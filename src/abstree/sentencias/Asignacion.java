package abstree.sentencias;

import resolid.Visitante;
import abstree.expresiones.Expresion;
import abstree.tipos.Tipo;
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
	
	public Tipo getTipo() throws UnsuportedOperation {
		return varleft.getTipo();
	}
	public boolean checkTipo() {
		int valorL;
		try {
			valorL = varleft.getTipo().valorT();
		} catch (UnsuportedOperation e1) {
			GestionErroresTiny.errorTipos(varleft.getFila(), 
					e1.getMessage());
			return false;
		}
		int valorR;
		try {
			valorR = expright.getTipo().valorT();
		} catch (UnsuportedOperation e1) {
			GestionErroresTiny.errorTipos(varleft.getFila(), 
					e1.getMessage());
			return false;
		}
		if(valorR!=valorL) {
			GestionErroresTiny.errorTipos(varleft.getFila(), 
					"El tipo de la expresion no coincide con el tipo de la variable");
			return false;
		}
		
		Expresion arrayL;
		Expresion arrayR;
		
		while(valorL>=2) {
			arrayL = varleft;
			arrayR = expright;
			
			try {
				if(arrayL.getTipo().numElems()!=arrayR.getTipo().numElems())
					GestionErroresTiny.errorTipos(varleft.getFila(), "Asignacion entre arrays de distinto tamaño.");
				return false;
			} catch (UnsuportedOperation e) {
				
			}
			
			try {
				arrayL = arrayL.elemAt(0);
				arrayR = arrayR.elemAt(0);
			} catch (UnsuportedOperation e) {
				e.printStackTrace();
			}
			
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
