package code;

import java.util.LinkedList;
import java.util.Stack;

import errors.CompilingException;

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
	
	public int getNumInstrStack(){
		int length = 0;
		for (CodeBlock block: stack){
			length += block.code.size();
		}
		return length;
	}
	
	public int getNumBlocksStack(){
		return stack.size();
	}

	private class CodeBlock {
		/**
		 * Crea un bloque de codigo
		 * @param codeLR Tipo de código: L (codeL) / R (codeR) / C (code)
		 * @param intbool Tipo de la expresion: i (int) / b (bool) / n (none)
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
