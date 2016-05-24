package abstree.sentencias;

import abstree.Programa;
import abstree.expresiones.Expresion;
import errors.UnsuportedOperation;

public class IfThenElse extends Sentencia{

	/**
	 * Crea sentencias <code>if-then-done</code> e <code>if-then-else-done</code>
	 * @param cond condicion del if
	 * @param codeif código del if
	 * @param codeelse código del else (null en caso de <code>if-then-done</code>)
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
	
	private Expresion cond;
	private Programa codeif;
	private Programa codeelse;
}
