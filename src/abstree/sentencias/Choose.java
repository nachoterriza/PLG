package abstree.sentencias;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import resolid.Visitante;
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
	
	public boolean checkTipo() throws UnsuportedOperation {
		if(var.getTipo()==new Int()) {
			Enumeration pCasos = casos.keys();
			boolean correct = true:
			while(pCasos.hasMoreElements()) {
				Programa codigo = pCasos.NextElement();
				correct = correct && casos.get(codigo).checkTipo();
			}
			if(correct)
				return true;
			else throw new UnsuportedOperation("Error en el código de CHOOSE.");
		} else throw new UnsuportedOperation("CHOOSE de índice no entero.");
	}

	@Override
	public void accept(Visitante v) {
		v.previsit(this);
		var.accept(v);
		Iterator<Entry<Integer,Programa>> it = casos.entrySet().iterator();
		Entry<Integer,Programa> e;
		while(it.hasNext()) {
			e = it.next();
			v.visit(e.getKey());
			e.getValue().accept(v);
		}
		v.postvisit(this);
	}


	private Expresion var;
	private Hashtable<Integer,Programa> casos;
}
