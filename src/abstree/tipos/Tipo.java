package abstree.tipos;

import resolid.Anfitrion;

public abstract class Tipo implements Anfitrion{

	public abstract int valorT();
	/**Devuelve el número de elementos de esta dimension del array*/
	public abstract int numElems();
	/**Devuelve el tamaño que ocupa una variable de este tipo en
	 * memoria (teniendo en cuenta todas las dimensiones y el tipo
	 * de los elementos)
	 */
	public abstract int tam();
	/**Devuelve el tamaño que ocupa un elemento teniendo en cuenta una
	 * dimension (array@pos). Los elementos
	 * a su vez pueden ser arrays
	 */
	public abstract int dsuper();
}
