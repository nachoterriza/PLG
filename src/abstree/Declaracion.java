package abstree;

import resolid.Anfitrion;
import resolid.Visitante;
import abstree.expresiones.Expresion;
import abstree.tipos.Tipo;

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

	private Tipo tipo;
	private String id;
	private Expresion valor;

}
