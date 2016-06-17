package abstree;

import abstree.expresiones.Expresion;
import abstree.tipos.Tipo;
import resolid.Anfitrion;
import resolid.Visitante;
import errors.UnsuportedOperation;

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
		if(valor.getTipo().valorT()==tipo.valorT())
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
