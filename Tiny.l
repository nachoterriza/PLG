package alex;

import errors.GestionErroresTiny;

%%
%cup
%line
%class AnalizadorLexicoTiny
%unicode
%public

%{
  private ALexOperations ops;
  private GestionErroresTiny errores;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public void fijaGestionErrores(GestionErroresTiny errores) {
   this.errores = errores;
  }
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

letra  = ([A-Z]|[a-z])
digito = [0-9]
numeroEntero = [0-9]*

separador = [ \t\r\b\n]
comentario = //[^\n]* 

identificador = {letra}({letra}|{digito})*

%%

"main start" 				{return ops.unidadMain();}
"end;" 						{return ops.unidadEnd();}
"method"					{return ops.unidadMethod();}
"takes input"				{return ops.unidadInput();}
"declares output"    		{return ops.unidadOutput();}
"makes"        				{return ops.unidadMakes();}
"returns output;"    		{return ops.unidadReturn();}

"if"   						{return ops.unidadIf();}
"then"    					{return ops.unidadThen();}
"else"    					{return ops.unidadElse();}

"while"    					{return ops.unidadWhile();}
"do"    					{return ops.unidadDo();}
"done;"    					{return ops.unidadDone();}

"considering"    			{return ops.unidadConsidering();}
"choose"    				{return ops.unidadChoose();}
"value"    					{return ops.unidadValue();}
"chosen;"    				{return ops.unidadChosen();}

"call"    					{return ops.unidadCall();}
"with"    					{return ops.unidadWith();}
"receiving"    				{return ops.unidadReceiving();}


"int"   					{return ops.unidadInt();}
"bool"    					{return ops.unidadBool();}
"array of"   				{return ops.unidadArrayOf();}


"all"   				{return ops.unidadAll();}
"to"   					{return ops.unidadTo();}
"{"   					{return ops.unidadLlaveA();}
","						{return ops.unidadComa();}
"}"   					{return ops.unidadLlaveC();}
"@"   					{return ops.unidadAt();}

"OR"   					{return ops.unidadOr();}
"AND"   				{return ops.unidadAnd();}
"NOT"   				{return ops.unidadNot();}
"TRUE"   				{return ops.unidadTrue();}
"FALSE"   				{return ops.unidadFalse();}

"=="   					{return ops.unidadEq();}
"!="   					{return ops.unidadNeq();}
"<"   					{return ops.unidadLt();}
">"   					{return ops.unidadGt();}
"<="   					{return ops.unidadLe();}
">="   					{return ops.unidadGe();}

"+"						{return ops.unidadSuma();}
"-"  					{return ops.unidadResta();}
"*" 					{return ops.unidadMul();}
"/"  					{return ops.unidadDiv();}

"(" 					{return ops.unidadPAp();}
")" 					{return ops.unidadPCierre();} 

"=" 					{return ops.unidadIgual();}
";" 					{return ops.unidadSemicolon();} 


{separador}               {}
{comentario}              {}
{identificador}           {return ops.unidadId();}
{numeroEntero}            {return ops.unidadEnt();}
[^]                       {errores.errorLexico(fila(),lexema());}  