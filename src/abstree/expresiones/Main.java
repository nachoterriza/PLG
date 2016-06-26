package src.abstree.expresiones;

import java.util.LinkedList;

import src.abstree.tipos.ArrayOf;
import src.abstree.tipos.Bool;
import src.abstree.tipos.Int;

public class Main {
	public static void main(String[] args) throws Exception {
		Expresion tres = new Number(3);
		Expresion cuatro = new Number(4);
		System.out.println(cuatro.toString());
		Expresion Suma;
		Suma = new Suma(tres, cuatro);
		System.out.println(Suma.getTipo().toString());
		Int test = new Int();
		if(Suma.getTipo().valorT()==test.valorT())
			System.out.println("Bien");
		else System.out.println("mal");
		
		Expresion tr = new True();
		Expresion fs = new False();
		Expresion and;
		and = new And(tr, fs);
		Bool test2 = new Bool();
		if(and.getTipo().valorT()==test2.valorT())
			System.out.println("Bien");
		else System.out.println("Mal");
		
		LinkedList<Expresion> testA = new LinkedList<Expresion>();
		testA.add(tres);
		testA.add(cuatro);
		ArrayWithKeys array = new ArrayWithKeys(testA);
		ArrayOf test3 = new ArrayOf(testA.size(),cuatro.getTipo());
		//System.out.println(testA.get(0).toString());
		if(array.getTipo().valorT()==test3.valorT())
			System.out.println("Bien");
		else System.out.println("Mal");
		
		
		
	}
}

