package src.abstree.expresiones;

import src.abstree.Declaracion;
import src.abstree.tipos.*;

public class Main {
	public static void main(String[] args) throws Exception {
		//Int a = new Int();
		//Identificador id = new Identificador("primero");
		Number tres = new Number(3);
		Number cuatro = new Number(4);
		System.out.println(cuatro.toString());
		//Expresion Entero = new (Expresion) (ExpresionUnaria) Int();
		//Expresion Entero;
		//Declaracion dec = new Declaracion(new Int(),"primero",Entero);

		Expresion Suma;
		//Expresion OtroEntero;
		//Declaracion dec2 = new Declaracion(new Int(),"segundo",OtroEntero);
		//OtroEntero.setRef(dec2);
		Suma = new Suma(tres, cuatro);
		System.out.println(Suma.getTipo().toString());
		if(Suma.getTipo()==new Int())
			System.out.println("Bien");
		else System.out.println("mal");
	}
}

