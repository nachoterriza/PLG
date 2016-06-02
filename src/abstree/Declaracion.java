package src.abstree;

import src.resolid.Anfitrion;
import src.resolid.Visitante;
import src.errors.UnsuportedOperation;
import src.abstree.expresiones.Expresion;
import src.abstree.tipos.Tipo;

public class Declaracion implements Anfitrion{
	
	public Declaracion(Tipo tipo, String id, Expresion valor){
		this.tipo = tipo;
		this.id = id;
		this.valor = valor;
	}
	
	public Tipo getTipo() {
		return tipo;
	}

	public String getId() {
		return id;
	}

	@Override
	public void accept(Visitante v) {
		v.previsit(this);
		tipo.accept(v);
		v.visit(id);
		if (valor != null)
			valor.accept(v);
		v.postvisit(this);
	}
	
	public boolean checkTipo() throws UnsuportedOperation {
		if(valor.getTipo()==tipo)
			return true;
		else throw new UnsuportedOperation("Declaracion con error de tipos.");
	}
	
	public Expresion getValor() {
		return valor;
	}

	private Tipo tipo;
	private String id;
	private Expresion valor;

}
