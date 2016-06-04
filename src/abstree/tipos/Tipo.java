package src.abstree.tipos;

import src.resolid.Anfitrion;

public abstract class Tipo implements Anfitrion{

	public boolean equalsT(Tipo t1) {
		return this.toString().equals(t1.toString());
	}
}
