package alex;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import java_cup.runtime.Symbol;
import asint.ClaseLexica;

public class Main {
   public static void main(String[] args) throws FileNotFoundException, IOException {
     Reader input = new InputStreamReader(new FileInputStream("maximo.code"));
     AnalizadorLexicoTiny al = new AnalizadorLexicoTiny(input);
     Symbol unidad;
     do {
       unidad = al.next_token();
       System.out.println("[ clase: " + terminalNames[unidad.sym] 
    		   + " , lexema: " + unidad.value + " ]");
     }
     while (unidad.sym != ClaseLexica.EOF);
    }
   
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
