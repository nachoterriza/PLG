package src.abstree;

import java.util.LinkedList;

import src.errors.UnsuportedOperation;
import src.resolid.Anfitrion;
import src.resolid.Visitante;

public class Funcion implements Anfitrion{
	
	public Funcion(String id, LinkedList<Declaracion> entrada, 
			LinkedList<Declaracion> salida, Programa programa) {
		this.id = id;
		this.entrada = entrada;
		this.salida = salida;
		this.programa = programa;
	}

	public String getId() {
		return id;
	}
	
	@Override
	public void accept(Visitante v) {
		v.previsit(this);
		v.visit(id);
		for(Declaracion d: entrada)
			d.accept(v);
		for(Declaracion d: salida)
			d.accept(v);
		programa.accept(v);
		v.postvisit(this);
	}

    public boolean checkTipo() throws UnsuportedOperation {
        LinkedList<Declaracion> ent = entrada;
        for(int i=0;i<ent.size();i++) {
            if(!ent.get(i).checkTipo())
                throw new UnsuportedOperation("Error de tipo en funcion (entrada).");
        }
        
        LinkedList<Declaracion> sal = salida;
        for(int j=0;j<sal.size();j++) {
            if(!sal.get(j).checkTipo())
                throw new UnsuportedOperation("Error de tipo en funcion (salida).");
        }
        
        if(!programa.checkTipo())
            throw new UnsuportedOperation("Error de tipo en funcion (programa).");
        return true;
    }

	private String id;
	private LinkedList<Declaracion> entrada;
	private LinkedList<Declaracion> salida;
	private Programa programa;
}
