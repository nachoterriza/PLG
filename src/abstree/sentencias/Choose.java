package abstree.sentencias;

import java.util.Enumeration;
import java.util.Hashtable;

import abstree.Programa;
import abstree.expresiones.Expresion;
import errors.UnsuportedOperation;

public class Choose extends Sentencia {

	public Choose(Expresion var, Hashtable<Integer,Programa> casos){
		this.var = var;
		this.casos = casos;
	}
	@Override
	public TipoS tipo() {
		return TipoS.CHOOSE;
	}
	@Override
	public int codeNum() throws UnsuportedOperation {
		Enumeration<Integer> keys = casos.keys();
		int max = keys.nextElement();
		int num;
		while (keys.hasMoreElements()) {
			num = keys.nextElement();
			if (num > max)
				max = num;
		}
		return max;
	}
	
	@Override
	public Expresion var() throws UnsuportedOperation {
		return var;
	}
	
	@Override
	public Programa codeAt(int i) throws UnsuportedOperation {
		Programa code = casos.get(i);
		if (code == null)
			throw new UnsuportedOperation("code "+i);
		else
			return code;
	}
	

	private Expresion var;
	private Hashtable<Integer,Programa> casos;
}
