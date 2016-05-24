package abstree.expresiones;

import abstree.Declaracion;
import errors.UnsuportedOperation;

//Hay que inicializar la referencia
public class Identificador extends Expresion {

	public Identificador(String id){
		this.id = id;
	}
	
	@Override
	public String id() throws UnsuportedOperation {
		return id;
	}
	
	@Override
	public Declaracion ref() throws UnsuportedOperation {
		return ref;
	}

	
	@Override
	public TipoE tipo() {
		return TipoE.ID;
	}

	private String id;
	private Declaracion ref;
}
