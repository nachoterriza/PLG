package abstree.expresiones;

import errors.UnsuportedOperation;
import abstree.tipos.ArrayOf;
import abstree.tipos.Bool;
import abstree.tipos.Int;
import abstree.tipos.Tipo;
import resolid.Visitante;

public class AllTo extends Expresion{

	
	public AllTo(Expresion op1, int num) {
		this.op1 = op1;
		this.num = num;
		this.fila = op1.getFila();
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
		int fila = op1.getFila();
		
		if(valor%2==0){
			Int test = new Int();
			if(valor==test.valorT())
				return new ArrayOf(num,new Int(),fila);
			else return findDim(valor,num,new Int(),fila);			
		} else {
			Bool testB = new Bool();
			if (valor==testB.valorT())
				return new ArrayOf(num,new Bool(),fila);
			else return findDim(valor,num,new Bool(),fila);
		}
	}
	
	private Tipo findDim(int valor, int num, Tipo tipo, int fila) {
		try {
			if(valor>=2) {
				int numHijo = op1.getTipo().numElems();
				return new ArrayOf(num,findDim(valor-2,numHijo,tipo,fila),fila); 
			}
			else return new ArrayOf(num,tipo,fila);
		} catch (UnsuportedOperation e) {
			e.printStackTrace();
		} return null;
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
