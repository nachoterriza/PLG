package abstree.sentencias;

import errors.GestionErroresTiny;
import errors.UnsuportedOperation;
import abstree.Programa;
import abstree.expresiones.Expresion;
import abstree.tipos.Bool;
import resolid.Visitante;

public class IfThenElse extends Sentencia{

	/**
	 * Crea sentencias <code>if-then-done</code> e <code>if-then-else-done</code>
	 * @param cond condicion del if
	 * @param codeif c�digo del if
	 * @param codeelse c�digo del else (null en caso de <code>if-then-done</code>)
	 */
	public IfThenElse(Expresion cond, Programa codeif, Programa codeelse){
		this.cond = cond;
		this.codeif = codeif;
		this.codeelse = codeelse;
	}
	@Override
	public TipoS tipo() {
		if (codeelse == null)
			return TipoS.IF;
		else
			return TipoS.IFELSE;
	}
	@Override
	public Expresion exp() throws UnsuportedOperation {
		return cond;
	}
	@Override
	public Programa codeAt(int i) throws UnsuportedOperation {
		switch(i){
			case 0: return codeif; 
			case 1: return codeelse; 
			default: throw new UnsuportedOperation("code "+i);
		}
	}
	
	@Override
	public void accept(Visitante v) {
		v.previsit(this);
		cond.accept(v);
		codeif.accept(v);
		if (codeelse != null)
			codeelse.accept(v);	
		v.postvisit(this);
	}


	public boolean checkTipo() {
		boolean ret = true;
		try {
			if (cond.getTipo().valorT()!=(new Bool()).valorT()){
				ret = false;
				GestionErroresTiny.errorTipos(cond.getFila(),"If de condicion no booleana.");
			}
		} catch (UnsuportedOperation e) {
			ret = false;
			GestionErroresTiny.errorTipos(cond.getFila(), e.getLocalizedMessage());
		}
		if (!codeif.checkTipo())
			ret = false;
		if (!codeelse.checkTipo())
			ret = false;
		return ret;
	}
	

	private Expresion cond;
	private Programa codeif;
	private Programa codeelse;
}
