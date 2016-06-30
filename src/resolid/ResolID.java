package resolid;


import errors.GestionErroresTiny;
import errors.IdentifyingIdException;
import errors.UnsuportedOperation;
import abstree.Declaracion;
import abstree.Funcion;
import abstree.Programa;
import abstree.expresiones.Identificador;
import abstree.sentencias.Call;

public class ResolID extends VisitorHelper {

	private TablaDeSimbolos tabla;
	
	public ResolID() {
		this.tabla = new TablaDeSimbolos();
	}
	
	@Override
	public void previsit(Declaracion node) {
		try {
			tabla.insertaIdV(node.getId(), node);
			//XXX TEST System.out.println("Variable " +node.getId() + " insertada");
		} catch (IdentifyingIdException e) {
			GestionErroresTiny.errorID(e.getFila(), e.getLocalizedMessage(), e.getFilaDecl());
		}
	}

	@Override
	public boolean previsit(Funcion node) {
		try {
			tabla.insertaIdF(node.getId(), node);
			//XXX TEST System.out.println("Funcion " +node.getId() + " insertada");
			tabla.abreBloqueV();
			//XXX TEST System.out.println("Bloque abierto");
		} catch (IdentifyingIdException e) {
			GestionErroresTiny.errorID(e.getFila(), e.getLocalizedMessage(), e.getFilaDecl());
		}
		return true;
	}

	@Override 
	public void postvisit(Funcion node) {
		tabla.cierraBloqueV();
		//XXX TEST System.out.println("Bloque cerrado");
	}

	@Override
	public void previsit(Programa node) {
		tabla.abreBloqueV();
		//XXX TEST System.out.println("Bloque abierto");
	}

	@Override
	public void postvisit(Programa node) {
		tabla.cierraBloqueV();
		//XXX TEST System.out.println("Bloque cerrado");
	}

	@Override 
	public void visit(Identificador node) {
		try {
			Declaracion ref = tabla.buscaIdV(node.id());
			//XXX TEST System.out.println("Variable " +node.id() + " encontrada en fila" + ref.getFila());
			node.setRef(ref);
		} catch (IdentifyingIdException e) {
			GestionErroresTiny.errorID(node.getFila(), e.getLocalizedMessage(), -1);
		} catch (UnsuportedOperation e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean previsit(Call node) {
		try {
			Funcion ref = tabla.buscaIdF(node.functionID());
			//XXX TEST System.out.println("Funcion " +node.functionID() + " encontrada en fila" + ref.getFila());
			node.setRef(ref);
		} catch (IdentifyingIdException e) {
			GestionErroresTiny.errorID(node.getFila(), e.getLocalizedMessage(), -1);
		} catch (UnsuportedOperation e) {
			e.printStackTrace();
		}
		return true;
	}

}
