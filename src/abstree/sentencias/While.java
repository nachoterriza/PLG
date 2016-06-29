package abstree.sentencias;

import errors.GestionErroresTiny;
import errors.UnsuportedOperation;
import abstree.Programa;
import abstree.expresiones.Expresion;
import abstree.tipos.Bool;
import resolid.Visitante;

public class While extends Sentencia {

	/**
	 * Crea sentencias <code>while-do-done</code>
	 * @param cond condicion del while
	 * @param code cuerpo del while
	 */
	public While(Expresion cond, Programa code){
		this.cond = cond;
		this.code = code;
	}
	@Override
	public TipoS tipo() {
		return TipoS.WHILE;
	}
	@Override
	public Expresion exp() throws UnsuportedOperation {
		return cond;
	}
	@Override
	public Programa codeAt(int i) throws UnsuportedOperation {
		if (i==0) return code;
		else throw new UnsuportedOperation("code "+i);
	}
	
	@Override
	public void accept(Visitante v) {
		v.previsit(this);
		cond.accept(v);
		code.accept(v);
		v.postvisit(this);
	}


	public boolean checkTipo() {
		Bool test = new Bool();
		try {	
			if(cond.getTipo().valorT()==test.valorT())
				return true;
			else throw new UnsuportedOperation("Condicion no booleana en bucle while.");
		} catch (UnsuportedOperation e) {
			GestionErroresTiny.errorTipos(cond.getFila(), e.getMessage());
		}
		return false;
	}
	
	private Expresion cond;
	private Programa code;

}
