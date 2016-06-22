package code;

import java.util.LinkedList;
import java.util.Stack;

import errors.CompilingException;

/**
 * Implementa una pila de trozos de código máquina, implementados como
 * listas de Strings. Existen 3 tipos diferentes de códigos:
 * <ul>
 * <li> CodeL: al ejecutarse, la pila crece en uno, dejando una
 * dirección en la pila. Corresponden a las direcciones de las variables
 * o de los elementos de arrays, especialmente cuando van a la izquierda de
 * las asignaciones.
 * <li> CodeR: al ejecutarse, la pila crece en uno, dejando un valor
 * en la pila. Corresponden a las expresiones, que suelen corresponderse con
 * la parte derecha de las asignaciones y las condiciones booleanas.
 * <li> CodeC (o Code): al ejecutarse, la pila no cambia de tamaño. Corresponde
 * a la ejecucion de sentencias completas tales como asignaciones, if, ifelse,
 * while...
 * </ul>
 * Es importante apilar los códigos del tipo específico, ya que evitará errores.
 * Además, la pila realiza conversiones implícitas de CodeL a CodeR cuando es necesario.
 * 
 */
public class CodeStack {

	public CodeStack(){
		this.stack = new Stack<CodeBlock>();
	}
	
	public void pushCodeL(LinkedList<String> code) throws CompilingException{
		this.stack.push(new CodeBlock('L', code));
	}
	
	public void pushCodeR(LinkedList<String> code) throws CompilingException{
		this.stack.push(new CodeBlock('R', code));
	}
	
	public void pushCodeC( LinkedList<String> code) throws CompilingException{
		this.stack.push(new CodeBlock('C', code));
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
	 * Devuelve el número de instrucciones máquina que hay en la pila
	 * entre todos los bloques.
	 * @return Número de bloques de instrucciones máquina en la pila
	 */
	public int getNumInstrStack(){
		int length = 0;
		for (CodeBlock block: stack){
			length += block.code.size();
		}
		return length;
	}
	
	/**
	 * Devuelve el número de bloques de código apilados.
	 * Usado en el CodeVisitor especialmente, ya que al final de la
	 * compilación todos los trocos de código se deberían haber podido 
	 * unir en uno sólo.
	 * @return Número de bloques de código apilados
	 */
	public int getNumBlocksStack(){
		return stack.size();
	}

	private class CodeBlock {
		/**
		 * Crea un bloque de codigo
		 * @param codeLR Tipo de codigo: L (codeL) / R (codeR) / C (code)
		 * @param code seccion de codigo
		 */
		public CodeBlock(char codeLR, LinkedList<String> code){
			this.codeLR = codeLR;
			this.code = code;
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
		private LinkedList<String> code;
	}

	private Stack<CodeBlock> stack;
}
