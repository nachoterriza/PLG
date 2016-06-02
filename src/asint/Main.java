package src.asint;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import java_cup.runtime.Symbol;
import src.alex.AnalizadorLexicoTiny;

public class Main {
   public static void main(String[] args) throws Exception {
     Reader input = new InputStreamReader(new FileInputStream("absurdo.code"));
	 AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
	 AnalizadorSintacticoTiny asint = new AnalizadorSintacticoTiny(alex);
	 asint.setScanner(alex);
	 Symbol s = asint.parse();
	/* System.out.println(""+ s.left
			 + s.right
			 + s.sym
			 + s.parse_state
			 + s.value);
	 System.out.println(""+ terminalNames[s.left]
			 + terminalNames[s.right]
			 + terminalNames[s.sym]
			 + terminalNames[s.parse_state]
			 + s.value);
	 */
	 
   }
   
  /* public static void paint(Symbol s) {
		System.out.println("(tree)");
		paint(s.left, 1);
		paint(s.right, 1);
		
	}
   
   public static void paint(Symbol s, int depth) {
		for (int i=0; i < depth; i++)
			System.out.print("  |");
		
		System.out.println("--" + s.sym);
		paint(s.left, depth+1);
		paint(s.right, depth+1);

	}*/
   
   public static final String[] terminalNames = new String[] {
		  "EOF",
		  "error",
		  "START",
		  "END",
		  "METHOD",
		  "INPUT",
		  "OUTPUT",
		  "MAKES",
		  "RETURN",
		  "IF",
		  "THEN",
		  "ELSE",
		  "WHILE",
		  "DO",
		  "DONE",
		  "CONSIDERING",
		  "CHOOSE",
		  "VALUE",
		  "CHOSEN",
		  "CALL",
		  "WITH",
		  "RECEIVING",
		  "INT",
		  "BOOL",
		  "ARRAYOF",
		  "ALL",
		  "TO",
		  "LLAVEA",
		  "LLAVEC",
		  "COMA",
		  "AT",
		  "OR",
		  "AND",
		  "NOT",
		  "TRUE",
		  "FALSE",
		  "EQ",
		  "NEQ",
		  "LT",
		  "GT",
		  "LE",
		  "GE",
		  "MAS",
		  "MENOS",
		  "POR",
		  "DIV",
		  "PAP",
		  "PCIERRE",
		  "IGUAL",
		  "SEMICOLON",
		  "ID",
		  "NUM",
		  "ENDOF"
		  };
}   
   
