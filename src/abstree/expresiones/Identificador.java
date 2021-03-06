package abstree.expresiones;

import errors.UnsuportedOperation;
import abstree.Declaracion;
import abstree.tipos.Tipo;
import resolid.Visitante;

//Hay que inicializar la referencia
public class Identificador extends Expresion {
	
	public Identificador(String id, int fila){
		this.id = id;
		this.fila = fila;
	}
	
	@Override
	public String id() throws UnsuportedOperation {
		return id;
	}
	
	@Override
	public String toString() {
		return id;
	}
	
	@Override
	public Declaracion ref() throws UnsuportedOperation {
		return ref;
	}
	
	@Override
	public void setRef(Declaracion ref) throws UnsuportedOperation {
		this.ref = ref;
	}

	
	@Override
	public TipoE tipo() {
		return TipoE.ID;
	}
	

	@Override
	public Tipo getTipo() throws UnsuportedOperation {
		return ref.getTipo();
	}

	@Override
	public void accept(Visitante v) {
		v.visit(this);
	}

	private String id;
	private Declaracion ref;
}
