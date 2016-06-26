package src.alex;

import src.asint.ClaseLexica;

public class ALexOperations {
  private AnalizadorLexicoTiny alex;
  public ALexOperations(AnalizadorLexicoTiny alex) {
   this.alex = alex;   
  }
  
  /*---Palabras reservadas---*/
  
  public UnidadLexica unidadMain() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.START,"main start"); 
} 
  public UnidadLexica unidadEnd() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.END,"end;"); 
} 
  public UnidadLexica unidadMethod() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.METHOD,"method"); 
} 
  public UnidadLexica unidadInput() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.INPUT,"takes input"); 
} 
  public UnidadLexica unidadOutput() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.OUTPUT,"declares output"); 
} 
  public UnidadLexica unidadMakes() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.MAKES,"makes"); 
} 
  public UnidadLexica unidadReturn() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.RETURN,"returns output;"); 
  } 
  
  
  public UnidadLexica unidadIf() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.IF,"if"); 
} 
  public UnidadLexica unidadThen() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.THEN,"then"); 
} 
  public UnidadLexica unidadElse() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.ELSE,"else"); 
} 
  public UnidadLexica unidadWhile() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.WHILE,"while"); 
} 
  public UnidadLexica unidadDo() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.DO,"do"); 
} 
  public UnidadLexica unidadDone() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.DONE,"done;"); 
} 
  
  
  public UnidadLexica unidadConsidering() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.CONSIDERING,"considering"); 
} 
  public UnidadLexica unidadChoose() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.CHOOSE,"choose"); 
} 
  public UnidadLexica unidadValue() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.VALUE,"value"); 
} 
  public UnidadLexica unidadChosen() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.CHOSEN,"chosen;"); 
} 
  
  
  public UnidadLexica unidadCall() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.CALL,"call"); 
} 
  public UnidadLexica unidadWith() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.WITH,"with"); 
} 
  public UnidadLexica unidadReceiving() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.RECEIVING,"receiving"); 
} 
  
  
  /*---Tipos---*/
  
  public UnidadLexica unidadInt() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.INT,"int"); 
} 
  public UnidadLexica unidadBool() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.BOOL,"bool"); 
} 
  public UnidadLexica unidadArrayOf() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.ARRAYOF,"array of"); 
} 
  
  
  /*---Operadores---*/
  
  public UnidadLexica unidadAll() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.ALL,"all"); 
} 
  public UnidadLexica unidadTo() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.TO,"to"); 
} 
  public UnidadLexica unidadLlaveA() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.LLAVEA,"{"); 
} 
  public UnidadLexica unidadLlaveC() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.LLAVEC,"}"); 
} 
  public UnidadLexica unidadComa() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.COMA,","); 
} 
  public UnidadLexica unidadAt() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.AT,"@"); 
} 
  
  
  
  public UnidadLexica unidadOr() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.OR,"OR"); 
} 
  public UnidadLexica unidadAnd() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.AND,"AND"); 
} 
  public UnidadLexica unidadNot() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.NOT,"NOT"); 
} 
  public UnidadLexica unidadTrue() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.TRUE,"TRUE"); 
} 
  public UnidadLexica unidadFalse() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.FALSE,"FALSE"); 
} 
  
  
  
  public UnidadLexica unidadEq() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.EQ,"=="); 
} 
  public UnidadLexica unidadNeq() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.NEQ,"!="); 
} 
  public UnidadLexica unidadLt() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.LT,"<"); 
} 
  public UnidadLexica unidadGt() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.GT,">"); 
} 
  public UnidadLexica unidadLe() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.LE,"<="); 
} 
  public UnidadLexica unidadGe() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.GE,">"); 
} 
  
  
  public UnidadLexica unidadSuma() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.MAS,"+"); 
  } 
  public UnidadLexica unidadResta() {
     return new UnidadLexica(alex.fila(),ClaseLexica.MENOS,"-"); 
  } 
  public UnidadLexica unidadMul() {
     return new UnidadLexica(alex.fila(),ClaseLexica.POR,"*"); 
  } 
  public UnidadLexica unidadDiv() {
     return new UnidadLexica(alex.fila(),ClaseLexica.DIV,"/"); 
  } 
  public UnidadLexica unidadPAp() {
     return new UnidadLexica(alex.fila(),ClaseLexica.PAP,"("); 
  } 
  public UnidadLexica unidadPCierre() {
     return new UnidadLexica(alex.fila(),ClaseLexica.PCIERRE,")"); 
  } 
  
  
  public UnidadLexica unidadIgual() {
     return new UnidadLexica(alex.fila(),ClaseLexica.IGUAL,"="); 
  } 
  
  public UnidadLexica unidadSemicolon() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.SEMICOLON,";"); 
	  }
  
  
  
  public UnidadLexica unidadId() {
     return new UnidadLexica(alex.fila(),ClaseLexica.ID,alex.lexema()); 
  } 
  
  public UnidadLexica unidadEnt() {
     return new UnidadLexica(alex.fila(),ClaseLexica.NUM,alex.lexema()); 
  } 
 
 
 

  public UnidadLexica unidadEof() {
     return new UnidadLexica(alex.fila(),ClaseLexica.EOF,"<EOF>"); 
  }
}
