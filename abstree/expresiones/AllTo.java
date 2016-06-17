package abstree.expresiones;

import abstree.tipos.ArrayOf;
import abstree.tipos.Bool;
import abstree.tipos.Int;
import abstree.tipos.Tipo;
import resolid.Visitante;
import errors.UnsuportedOperation;

public class AllTo extends Expresion{

	public AllTo(Expresion op1, int num) {
		this.op1 = op1;
		this.num = num;
	}
	
	@Override
	public int num() throws UnsuportedOperation {
		return num;
	}
	

	@Override
	public Expresion op1() throws UnsuportedOperation {
		return op1;
	}
	
	@Override
	public TipoE tipo() {
		return TipoE.ALLTO;
	}
	
	public Tipo getTipo() throws UnsuportedOperation {
		int valor = op1.getTipo().valorT();
		
		if(valor%2==0){
			Int test = new Int();
			if(valor==test.valorT())
				return new ArrayOf(num,new Int());
			else return findDim(valor,num,new Int());			
		} else {
			Bool testB = new Bool();
			if (valor==testB.valorT())
				return new ArrayOf(num,new Bool());
			else return findDim(valor,num,new Bool());
		}
	}
	
	private Tipo findDim(int valor, int num, Tipo tipo) {
		if(valor>=4)
			return new ArrayOf(num,findDim(valor-2,num,tipo));
		else return new ArrayOf(num,tipo);
	}

	@Override
	public void accept(Visitante v) {
		v.previsit(this);
		v.visit(num);
		op1.accept(v);
		v.postvisit(this);
	}

	private Expresion op1;
	private int num;

}
