package src.resolid;


/**
 * Esta clase permite a un visitante recorrer el 
 * arbol. un accept debe hacer un visit, o bien hacer un
 * presVisit sobre si mismo, un accept de sus hijos y un 
 * postvisit sobre si mismo
 */
public interface Anfitrion {
	public void accept(Visitante v);
}
