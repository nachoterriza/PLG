package code;

import java.util.LinkedList;
import java.util.Stack;

import abstree.tipos.ArrayOf;
import abstree.tipos.Tipo;
import errors.CompilingException;

/**
 * Implementa una pila de trozos de c�digo m�quina, implementados como
 * listas de Strings. Existen 3 tipos diferentes de c�digos:
 * <ul>
 * <li> CodeL: al ejecutarse, la pila crece en uno, dejando una
 * direcci�n en la pila. Corresponden a las direcciones de las variables
 * o de los elementos de arrays, especialmente cuando van a la izquierda de
 * las asignaciones.
 * <li> CodeR: al ejecutarse, la pila crece en uno (o en mas si el valor que
 *  maneja es un array), dejando un valor
 * en la pila. Corresponden a las expresiones, que suelen corresponderse con
 * la parte derecha de las asignaciones y las condiciones booleanas.
 * <li> CodeC (o Code): al ejecutarse, la pila no cambia de tama�o. Corresponde
 * a la ejecucion de sentencias completas tales como asignaciones, if, ifelse,
 * while...
 * </ul>
 * Es importante apilar los c�digos del tipo espec�fico, ya que evitar� errores.
 * Adem�s, la pila realiza conversiones impl�citas de CodeL a CodeR cuando es necesario.
 * 
 */
public class CodeStack {

	public CodeStack(){
		this.stack = new Stack<CodeBlock>();
	}
	
	public void pushCodeL(LinkedList<String> code, Tipo tipo) throws CompilingException{
		this.stack.push(new CodeBlock('L', code, tipo));
	}
	
	public void pushCodeR(LinkedList<String> code) throws CompilingException{
		this.stack.push(new CodeBlock('R', code, null));
	}
	
	public void pushCodeC( LinkedList<String> code) throws CompilingException{
		this.stack.push(new CodeBlock('C', code, null));
	}
	
	public LinkedList<String> popCodeL() throws CompilingException{
		CodeBlock code = this.stack.pop();
		return code.codeL();
	}
	
	public LinkedList<String> popCodeR() throws CompilingException{
		CodeBlock code = this.stack.pop();
		return code.codeR();
	}
	
	public LinkedList<String> popCodeC() throws CompilingException{
		CodeBlock code = this.stack.pop();
		return code.codeC();
	}
	
	/**
	 * Devuelve el n�mero de instrucciones m�quina que hay en la pila
	 * entre todos los bloques.
	 * @return N�mero de bloques de instrucciones m�quina en la pila
	 */
	public int getNumInstrStack(){
		int length = 0;
		for (CodeBlock block: stack){
			length += block.code.size();
		}
		return length;
	}
	
	/**
	 * Devuelve el n�mero de bloques de c�digo apilados.
	 * Usado en el CodeVisitor especialmente, ya que al final de la
	 * compilaci�n todos los trocos de c�digo se deber�an haber podido 
	 * unir en uno s�lo.
	 * @return N�mero de bloques de c�digo apilados
	 */
	public int getNumBlocksStack(){
		return stack.size();
	}

	private class CodeBlock {
		/**
		 * Crea un bloque de codigo
		 * @param codeLR Tipo de codigo: L (codeL) / R (codeR) / C (code)
		 * @param code seccion de codigo
		 * @param tipo Tipo de la variable (para codeL)
		 */
		public CodeBlock(char codeLR, LinkedList<String> code, Tipo tipo){
			this.codeLR = codeLR;
			this.code = code;
			this.tipo = tipo;
		}
		
		public LinkedList<String> codeC() throws CompilingException{
			if (codeLR == 'C')
				return code;
			else
				throw new CompilingException("Se esperaba un codeC y se ha obtenido un code"+codeLR);
		}
		
		public LinkedList<String> codeL() throws CompilingException{
			if (codeLR == 'L')
				return code;
			else
				throw new CompilingException("Se esperaba un codeL y se ha obtenido un code"+codeLR);
		}
		
		public LinkedList<String> codeR() throws CompilingException{
			if (codeLR == 'L'){
				if (tipo instanceof ArrayOf) //Tipo compuesto
					code.add(IR.movs(tipo.tam()));
				else  //Tipo simple
					code.add(IR.ind());
				codeLR = 'R';
				return code;
			} 
			else if (codeLR == 'C')
				throw new CompilingException("Se esperaba un codeR y se ha obtenido un code"+codeLR);
			else
				return code;
		}

		private char codeLR;
		private Tipo tipo;
		private LinkedList<String> code;
	}

	private Stack<CodeBlock> stack;
}
