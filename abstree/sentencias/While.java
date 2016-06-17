package abstree.sentencias;

import resolid.Visitante;
import abstree.Programa;
import abstree.expresiones.Expresion;
import abstree.tipos.Bool;
import errors.UnsuportedOperation;

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
		else 	throw new UnsuportedOperation("code "+i);
	}
	
	@Override
	public void accept(Visitante v) {
		v.previsit(this);
		cond.accept(v);
		code.accept(v);
		v.postvisit(this);
	}


	public boolean checkTipo() throws UnsuportedOperation {
		Bool test = new Bool();
		if(cond.getTipo().valorT()==test.valorT())
			return true;
		else throw new UnsuportedOperation("Condición no booleana en bucle while.");
	}
	
	private Expresion cond;
	private Programa code;

}
