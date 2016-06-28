package code;

import java.util.LinkedList;

import errors.CompilingException;
import abstree.expresiones.TipoE;

/**
 * Clase InstructionRepertory que reune todas las instrucciones mï¿½quina y 
 * las centraliza para que su modificaciï¿½n sea mï¿½s sencilla. Actualmente, 
 * los saltos que se usan en las sentencias son saltos relativos, que se transforman
 *  en absolutos con la funcion {@link IR#relToAbsJumps(LinkedList)}
 */
public class IR {
	public static String add(){return "add;";}
	public static String sub(){return "sub;";}
	public static String mul(){return "mul;";}
	public static String div(){return "div;";}
	public static String neg(){return "neg;";}
	
	public static String and(){return "and;";}
	public static String or(){return "or;";}
	public static String not(){return "not;";}

	public static String eq(){return "equ;";}
	public static String ge(){return "geq;";}
	public static String le(){return "leq;";}
	public static String lt(){return "les;";}
	public static String gt(){return "grt;";}
	public static String neq(){return "neq;";}
	
	public static String ldcAddr(int c){return "lda 0 "+c+";";}
	public static String ldcInt(int c){return "ldc "+c+";";}
	public static String ldcTrue(){return "ldc true;";}
	public static String ldcFalse(){return "ldc false;";}
	public static String ind(){return "ind;";}
	public static String sto(){return "sto;";}
	
	public static String load0(){return "ldo 0;";}
	public static String store0(){return "sro 0;";}
	public static String load1(){return "ldo 1;";}
	public static String store1(){return "sro 1;";}
	
	public static String dup(){return "dpl;";}
	
	/**
	 * Instruccion de salto a una función
	 * @param lparam longitud de la zona de parametros de la funcion llamada.
	 * @param dir direccion de comienzo del código de la funcion llamada.
	 * @return
	 */
	public static String callj(int lparam, int dir){return "cup "+lparam+" "+dir+";";}
	public static String returnj(){return "retp;";}
	public static String uncondj(int dir){return "ujp "+dir+";";}
	public static String condj(int dir){return "fjp "+dir+";";}
	public static String casej(int dir){return "ixj "+dir+";";}

	
	public static String startfun(int lvar){return "ssp "+lvar+";";}
	public static String startcall(){return "mst 0;";}
	public static String movs(int size){return "movs "+size+";";}
	public static String access(int tam){return "ixa "+tam+";";}
	public static String stop(){return "stp;";}
	
	/**
	 * Obtiene la instruccion correspondiente a una expresion binaria
	 * @param t Tipo de la expresiï¿½n
	 * @return Instrucciï¿½n mï¿½quina correspondiente
	 * @throws CompilingException si el tipo no se corresponde con el de
	 * una expresion binaria admitida
	 */
	public static String binary(TipoE t) throws CompilingException{
		String ins;
		switch (t){

		case AND: ins = IR.and();
			break;
		case DIV: ins = IR.div();
			break;
		case EQ: ins = IR.eq();
			break;
		case GE: ins = IR.ge();
			break;
		case GT: ins = IR.gt();
			break;
		case LE: ins = IR.le();
			break;
		case LT: ins = IR.lt();
			break;
		case MULT: ins = IR.mul();
			break;
		case NEQ: ins = IR.neq();
			break;
		case OR: ins = IR.or();
			break;
		case RESTA: ins = IR.sub();
			break;
		case SUMA: ins = IR.add();
			break;
		default: throw new CompilingException("Instruccion binaria no reconocida");
		
		}
		return ins;
	}
	
	/**
	 * Transforma todos los saltos de este bloque de relativos a absolutos,
	 * tomando como instrucciï¿½n cero la primera de este bloque.
	 * @param code Bloque de codigo original
	 * @return Bloque de cï¿½digo transformado
	 */
	public static LinkedList<String> relToAbsJumps(LinkedList<String> code){
		LinkedList<String> newcode= new LinkedList<String>();
		int i=0;
		String s;
		int dir;
		for (String instr: code){
			s = (String) instr.subSequence(0, 4);
			if (s.equalsIgnoreCase("ujp ") 
					|| s.equalsIgnoreCase("fjp ")
					|| s.equalsIgnoreCase("ixj ")
				)
			{
				dir = Integer.parseInt(instr.substring(4, instr.length()-1));
				dir = dir + i;
				instr = s+dir+';';
			}
			newcode.add(instr);
			i++;
		}
		return newcode;
	}
	
	/**
	 * Dado que el main en el codigo maquina va antes que las funciones,
	 * añade el tamaño del main a los saltos a funciones
	 * @param code Bloque de codigo original
	 * @param tam tamaño del main, a sumar a los saltos a funciones
	 * @return Bloque de codigo transformado
	 */
	public static LinkedList<String> adjustFuncionJumps(LinkedList<String> code, int tam){
		LinkedList<String> newcode= new LinkedList<String>();
		String s;
		int dir;
		for (String instr: code){
			s = (String) instr.subSequence(0, 4);
			if ( s.equalsIgnoreCase("cup ") ){
				dir = Integer.parseInt(instr.substring(4, instr.length()-1));
				dir = dir + tam;
				instr = s+dir+';';
			}
			newcode.add(instr);
		}
		return newcode;
	}
}
