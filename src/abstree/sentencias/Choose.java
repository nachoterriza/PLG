package abstree.sentencias;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;

import errors.GestionErroresTiny;
import errors.UnsuportedOperation;
import abstree.Programa;
import abstree.expresiones.Expresion;
import abstree.tipos.Int;
import resolid.Visitante;

public class Choose extends Sentencia {

	public Choose(Expresion var, Hashtable<Integer,Programa> casos){
		this.var = var;
		this.casos = casos;
	}
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
	
	public boolean checkTipo() {
		Int test = new Int();
		try {
			if(var.getTipo().valorT()==test.valorT()) {
				Enumeration<Programa> pCasos = casos.elements();
				boolean correct = true;
				while(pCasos.hasMoreElements()) {
					Programa codigo = pCasos.nextElement();
					correct = correct && codigo.checkTipo();
				}
				if(correct)
					return true;
				else throw new UnsuportedOperation("Error en el codigo de CHOOSE.");
			} else throw new UnsuportedOperation("CHOOSE de indice no entero.");
		} catch (UnsuportedOperation e) {
			GestionErroresTiny.errorTipos(var.getFila(), e.getMessage());
		}
		return false;
	}

	@Override
	public void accept(Visitante v) {
		boolean cont =  v.previsit(this);
		if (!cont) return;
		
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
