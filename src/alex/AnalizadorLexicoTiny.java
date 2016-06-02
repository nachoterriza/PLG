package src.alex;
import src.errors.GestionErroresTiny;


public class AnalizadorLexicoTiny implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 65536;
	private final int YY_EOF = 65537;

  private ALexOperations ops;
  private GestionErroresTiny errores;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public void fijaGestionErrores(GestionErroresTiny errores) {
   this.errores = errores;
  }
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public AnalizadorLexicoTiny (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public AnalizadorLexicoTiny (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private AnalizadorLexicoTiny () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;

  ops = new ALexOperations(this);
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NO_ANCHOR,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NOT_ACCEPT,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NOT_ACCEPT,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NOT_ACCEPT,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NOT_ACCEPT,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NOT_ACCEPT,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NOT_ACCEPT,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NOT_ACCEPT,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NOT_ACCEPT,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NOT_ACCEPT,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NOT_ACCEPT,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NOT_ACCEPT,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NOT_ACCEPT,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NOT_ACCEPT,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NOT_ACCEPT,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NOT_ACCEPT,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NOT_ACCEPT,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NOT_ACCEPT,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NOT_ACCEPT,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NOT_ACCEPT,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NOT_ACCEPT,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NOT_ACCEPT,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NOT_ACCEPT,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NOT_ACCEPT,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NOT_ACCEPT,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NOT_ACCEPT,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NO_ANCHOR,
		/* 125 */ YY_NO_ANCHOR,
		/* 126 */ YY_NO_ANCHOR,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NO_ANCHOR,
		/* 129 */ YY_NO_ANCHOR,
		/* 130 */ YY_NO_ANCHOR,
		/* 131 */ YY_NO_ANCHOR,
		/* 132 */ YY_NO_ANCHOR,
		/* 133 */ YY_NO_ANCHOR,
		/* 134 */ YY_NO_ANCHOR,
		/* 135 */ YY_NO_ANCHOR,
		/* 136 */ YY_NO_ANCHOR,
		/* 137 */ YY_NO_ANCHOR,
		/* 138 */ YY_NO_ANCHOR,
		/* 139 */ YY_NO_ANCHOR,
		/* 140 */ YY_NO_ANCHOR,
		/* 141 */ YY_NO_ANCHOR,
		/* 142 */ YY_NO_ANCHOR,
		/* 143 */ YY_NO_ANCHOR,
		/* 144 */ YY_NO_ANCHOR,
		/* 145 */ YY_NO_ANCHOR,
		/* 146 */ YY_NO_ANCHOR,
		/* 147 */ YY_NO_ANCHOR,
		/* 148 */ YY_NO_ANCHOR,
		/* 149 */ YY_NO_ANCHOR,
		/* 150 */ YY_NO_ANCHOR,
		/* 151 */ YY_NO_ANCHOR,
		/* 152 */ YY_NO_ANCHOR,
		/* 153 */ YY_NO_ANCHOR,
		/* 154 */ YY_NO_ANCHOR,
		/* 155 */ YY_NO_ANCHOR,
		/* 156 */ YY_NO_ANCHOR,
		/* 157 */ YY_NO_ANCHOR,
		/* 158 */ YY_NO_ANCHOR,
		/* 159 */ YY_NO_ANCHOR,
		/* 160 */ YY_NO_ANCHOR,
		/* 161 */ YY_NO_ANCHOR,
		/* 162 */ YY_NO_ANCHOR,
		/* 163 */ YY_NO_ANCHOR,
		/* 164 */ YY_NO_ANCHOR,
		/* 165 */ YY_NO_ANCHOR,
		/* 166 */ YY_NO_ANCHOR,
		/* 167 */ YY_NO_ANCHOR,
		/* 168 */ YY_NO_ANCHOR,
		/* 169 */ YY_NO_ANCHOR,
		/* 170 */ YY_NO_ANCHOR,
		/* 171 */ YY_NO_ANCHOR,
		/* 172 */ YY_NO_ANCHOR,
		/* 173 */ YY_NO_ANCHOR,
		/* 174 */ YY_NO_ANCHOR,
		/* 175 */ YY_NO_ANCHOR,
		/* 176 */ YY_NO_ANCHOR,
		/* 177 */ YY_NO_ANCHOR,
		/* 178 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"51:8,52:2,50,51:2,52,51:18,5,41,51:6,48,49,46,44,26,45,51,47,55:10,51,11,42" +
",40,43,51,28,31,53:2,33,36,37,53:5,38,53,32,29,53:2,30,39,34,35,53:5,51:6,2" +
",23,17,10,9,19,21,12,3,54,14,18,1,4,13,15,54,8,6,7,16,22,20,54,24,54,25,51," +
"27,51:65410,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,179,
"0,1,2,1:6,3,4,5,6,1:3,7,1:2,8:2,9,8,1:4,10,8:5,1,8:5,11,8,1,8:5,1:2,8,1:2,8" +
",1:2,12,13,1,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34" +
",35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59" +
",60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84" +
",85,86,87,88,89,90,91,92,93,94,95,96,97,98,8,99,100,101,102,103,104,105,106" +
",107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,12" +
"5,126,127,128,8,129,130,131,132")[0];

	private int yy_nxt[][] = unpackFromString(133,56,
"1,2,110,56,174,3,174,59,176,136,61,4,174:5,137,174:2,138,174,177,139,174,5," +
"6,7,8,63,174,112,113,174,140,174:2,178,174:2,9,10,11,12,13,14,15,16,17,18,3" +
",57,3,174:2,55,-1:57,174,141,174:2,-1,174:3,142,174,-1,174:13,-1:4,143:11,-" +
"1:13,143,174,143,-1:40,23,-1:55,24,-1:55,25,-1:55,26,-1:62,27,-1:9,174:4,-1" +
",174:5,-1,174:13,-1:4,143:11,-1:13,143,174,143,-1,174:3,123,-1,174:5,-1,174" +
":13,-1:4,143:11,-1:13,143,174,143,-1,27:49,-1,27:5,-1:6,58,-1:104,55,-1,174" +
":3,67,-1,174:5,-1,174:7,19,174:5,-1:4,143:11,-1:13,143,174,143,-1:7,64,-1:4" +
"9,174,145,174:2,-1,174:5,-1,114,20,174:11,-1:4,143:11,-1:13,143,174,143,-1:" +
"13,66,-1:43,174:4,-1,174:3,147,174,-1,174,21,174:11,-1:4,143:11,-1:13,143,1" +
"74,143,-1:3,68,-1:53,174:4,-1,174:5,-1,174:13,-1:4,143,22,143:9,-1:13,143,1" +
"74,143,-1:2,70,-1:54,174:4,-1,174:5,-1,174:6,28,174:6,-1:4,143:11,-1:13,143" +
",174,143,-1:19,48,-1:37,174:4,-1,174,29,174:3,-1,174:13,-1:4,143:11,-1:13,1" +
"43,174,143,-1:4,72,-1:52,174:4,-1,174:5,-1,174:13,-1:4,143:4,30,143:6,-1:13" +
",143,174,143,-1:8,76,-1:48,174:4,-1,174:5,-1,174:13,-1:4,143:5,31,143:5,-1:" +
"13,143,174,143,-1:15,78,-1:41,174:3,32,-1,174:5,-1,174:13,-1:4,143:11,-1:13" +
",143,174,143,-1:13,111,-1:43,174:4,-1,174:5,33,174:13,-1:4,143:11,-1:13,143" +
",174,143,-1:7,50,-1:49,174:4,-1,174:3,34,174,-1,174:13,-1:4,143:11,-1:13,14" +
"3,174,143,-1:16,82,-1:40,174:4,-1,174:5,-1,174:6,35,174:6,-1:4,143:11,-1:13" +
",143,174,143,-1:13,86,-1:43,174:4,-1,174:5,-1,36,174:12,-1:4,143:11,-1:13,1" +
"43,174,143,-1:7,51,-1:49,174:4,-1,174:5,-1,174:6,37,174:6,-1:4,143:11,-1:13" +
",143,174,143,-1:7,88,-1:49,174:4,-1,174:5,-1,174:13,-1:4,143:7,38,143:3,-1:" +
"13,143,174,143,-1:16,90,-1:40,174:4,39,174:5,-1,174:13,-1:4,143:11,-1:13,14" +
"3,174,143,-1:15,92,-1:41,174:4,-1,40,174:4,-1,174:13,-1:4,143:11,-1:13,143," +
"174,143,-1:7,94,-1:49,174:4,-1,174:5,41,174:13,-1:4,143:11,-1:13,143,174,14" +
"3,-1:16,96,-1:40,174:4,-1,174:3,42,174,-1,174:13,-1:4,143:11,-1:13,143,174," +
"143,-1:15,98,-1:41,174:4,-1,174:3,43,174,-1,174:13,-1:4,143:11,-1:13,143,17" +
"4,143,-1:7,100,-1:49,174:4,-1,174:5,-1,174:13,-1:4,143:7,44,143:3,-1:13,143" +
",174,143,-1:16,102,-1:40,174:4,-1,174:4,45,-1,174:13,-1:4,143:11,-1:13,143," +
"174,143,-1:11,53,-1:45,174:4,60,174:5,-1,174:13,-1:4,143:11,-1:13,143,174,1" +
"43,-1:7,54,-1:49,174:4,62,174:5,-1,174:13,-1:4,143:11,-1:13,143,174,143,-1," +
"174:4,-1,174:3,46,174,-1,174:13,-1:4,143:11,-1:13,143,174,143,-1,174:4,-1,1" +
"74:5,47,174:13,-1:4,143:11,-1:13,143,174,143,-1,174:4,74,174:5,-1,174:13,-1" +
":4,143:11,-1:13,143,174,143,-1,174:4,-1,174:5,-1,174:9,49,174:3,-1:4,143:11" +
",-1:13,143,174,143,-1,174:4,80,174:5,-1,174:13,-1:4,143:11,-1:13,143,174,14" +
"3,-1,174:4,-1,174:5,-1,174:9,52,174:3,-1:4,143:11,-1:13,143,174,143,-1,174:" +
"4,-1,174:2,144,174:2,-1,174:6,65,174:6,-1:4,143:11,-1:13,143,174,143,-1:16," +
"84,-1:40,174:4,-1,174:5,-1,174:13,-1:4,143:3,69,143:7,-1:13,143,174,143,-1," +
"174:4,-1,174:5,-1,174:13,-1:4,71,143:10,-1:13,143,174,143,-1,174:4,-1,174:3" +
",73,174,-1,174:13,-1:4,143:11,-1:13,143,174,143,-1,174:4,-1,174:4,75,-1,174" +
":13,-1:4,143:11,-1:13,143,174,143,-1,174:4,-1,77,174:4,-1,174:13,-1:4,143:1" +
"1,-1:13,143,174,143,-1,174:4,-1,174:5,-1,174:6,79,174:6,-1:4,143:11,-1:13,1" +
"43,174,143,-1,174:4,-1,174,81,174:3,-1,174:13,-1:4,143:11,-1:13,143,174,143" +
",-1,174:4,-1,174:5,-1,174,83,174:11,-1:4,143:11,-1:13,143,174,143,-1,174:4," +
"-1,174:5,-1,174:13,-1:4,143:6,85,143:4,-1:13,143,174,143,-1,174:3,87,-1,174" +
":5,-1,174:13,-1:4,143:11,-1:13,143,174,143,-1,174:4,-1,174:3,89,174,-1,174:" +
"13,-1:4,143:11,-1:13,143,174,143,-1,174:4,-1,174:3,91,174,-1,174:13,-1:4,14" +
"3:11,-1:13,143,174,143,-1,174:4,-1,174:5,-1,174:6,93,174:6,-1:4,143:11,-1:1" +
"3,143,174,143,-1,174:4,-1,174:5,-1,174:4,95,174:8,-1:4,143:11,-1:13,143,174" +
",143,-1,174:4,-1,174:5,-1,174:13,-1:4,143:10,97,-1:13,143,174,143,-1,174:4," +
"-1,174:5,-1,174,99,174:11,-1:4,143:11,-1:13,143,174,143,-1,174:4,-1,174:5,-" +
"1,174:12,101,-1:4,143:11,-1:13,143,174,143,-1,174:4,-1,103,174:4,-1,174:13," +
"-1:4,143:11,-1:13,143,174,143,-1,174:4,-1,104,174:4,-1,174:13,-1:4,143:11,-" +
"1:13,143,174,143,-1,174:3,105,-1,174:5,-1,174:13,-1:4,143:11,-1:13,143,174," +
"143,-1,174:4,-1,106,174:4,-1,174:13,-1:4,143:11,-1:13,143,174,143,-1,174:3," +
"107,-1,174:5,-1,174:13,-1:4,143:11,-1:13,143,174,143,-1,174:4,-1,108,174:4," +
"-1,174:13,-1:4,143:11,-1:13,143,174,143,-1,174:3,109,-1,174:5,-1,174:13,-1:" +
"4,143:11,-1:13,143,174,143,-1,174:3,115,-1,174:5,-1,174:6,116,174:6,-1:4,14" +
"3:11,-1:13,143,174,143,-1,174,117,174:2,-1,174:5,-1,148,149,174:11,-1:4,143" +
":11,-1:13,143,174,143,-1,174:2,118,174,-1,174:5,-1,150,174:12,-1:4,143:11,-" +
"1:13,143,174,143,-1,174:4,-1,174:5,-1,174,119,174:11,-1:4,143:11,-1:13,143," +
"174,143,-1,174:4,-1,174:5,-1,174:13,-1:4,143,120,143:9,-1:13,143,174,143,-1" +
",174:2,121,174,-1,174:5,-1,174:2,122,174:10,-1:4,143:11,-1:13,143,174,143,-" +
"1,174:4,-1,174,153,174:3,-1,174:13,-1:4,143:11,-1:13,143,174,143,-1,174:4,-" +
"1,174:2,154,174:2,-1,174:13,-1:4,143:11,-1:13,143,174,143,-1,174:4,-1,174:5" +
",-1,174:2,155,174:10,-1:4,143:11,-1:13,143,174,143,-1,174:4,-1,174,156,174:" +
"3,-1,174:5,157,174:7,-1:4,143:11,-1:13,143,174,143,-1,174:4,-1,174:5,-1,174" +
":5,158,174:7,-1:4,143:11,-1:13,143,174,143,-1,174:4,-1,174:5,-1,174,159,174" +
":11,-1:4,143:11,-1:13,143,174,143,-1,174:3,160,-1,174:5,-1,174:13,-1:4,143:" +
"11,-1:13,143,174,143,-1,174:2,124,174,-1,174:5,-1,174:13,-1:4,143:11,-1:13," +
"143,174,143,-1,174:4,-1,174:5,-1,174:6,125,174:6,-1:4,143:11,-1:13,143,174," +
"143,-1,174:4,-1,174:5,-1,174:13,-1:4,143:9,126,143,-1:13,143,174,143,-1,174" +
":4,-1,174:5,-1,127,174:12,-1:4,143:11,-1:13,143,174,143,-1,174,128,174:2,-1" +
",174:5,-1,174:13,-1:4,143:11,-1:13,143,174,143,-1,174:4,-1,174:3,129,174,-1" +
",174:13,-1:4,143:11,-1:13,143,174,143,-1,174:4,-1,174:5,-1,174:4,161,174:8," +
"-1:4,143:11,-1:13,143,174,143,-1,174:4,-1,174:3,162,174,-1,174:13,-1:4,143:" +
"11,-1:13,143,174,143,-1,174:4,-1,174:5,-1,174:6,163,174:6,-1:4,143:11,-1:13" +
",143,174,143,-1,174:4,-1,164,174:4,-1,174,130,174:11,-1:4,143:11,-1:13,143," +
"174,143,-1,174:4,-1,175,174:4,-1,174:13,-1:4,143:11,-1:13,143,174,143,-1,17" +
"4:4,-1,174:2,165,174:2,-1,174:13,-1:4,143:11,-1:13,143,174,143,-1,174:2,166" +
",174,-1,174:5,-1,174:13,-1:4,143:11,-1:13,143,174,143,-1,174,167,174:2,-1,1" +
"74:5,-1,174:13,-1:4,143:11,-1:13,143,174,143,-1,174:4,-1,174:3,131,174,-1,1" +
"74:13,-1:4,143:11,-1:13,143,174,143,-1,174:3,132,-1,174:5,-1,174:13,-1:4,14" +
"3:11,-1:13,143,174,143,-1,174:4,-1,174:5,-1,174:10,169,174:2,-1:4,143:11,-1" +
":13,143,174,143,-1,174:4,-1,174:2,170,174:2,-1,174:13,-1:4,143:11,-1:13,143" +
",174,143,-1,174:4,-1,174:4,171,-1,174:13,-1:4,143:11,-1:13,143,174,143,-1,1" +
"74:2,133,174,-1,174:5,-1,174:13,-1:4,143:11,-1:13,143,174,143,-1,174:4,-1,1" +
"74:3,134,174,-1,174:13,-1:4,143:11,-1:13,143,174,143,-1,174:4,-1,174:3,172," +
"174,-1,174:13,-1:4,143:11,-1:13,143,174,143,-1,174:4,-1,174:2,173,174:2,-1," +
"174:13,-1:4,143:11,-1:13,143,174,143,-1,174:2,135,174,-1,174:5,-1,174:13,-1" +
":4,143:11,-1:13,143,174,143,-1,174:2,168,174,-1,174:5,-1,174:13,-1:4,143:11" +
",-1:13,143,174,143,-1,174:4,-1,174:3,146,174,-1,174:13,-1:4,143:11,-1:13,14" +
"3,174,143,-1,174,151,174:2,-1,174:5,-1,174:13,-1:4,143:11,-1:13,143,174,143" +
",-1,174:4,-1,174:5,-1,174:13,-1:4,143:2,152,143:8,-1:13,143,174,143");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

  return ops.unidadEof();
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 0:
						{return ops.unidadEnt();}
					case -2:
						break;
					case 1:
						
					case -3:
						break;
					case 2:
						{return ops.unidadId();}
					case -4:
						break;
					case 3:
						{}
					case -5:
						break;
					case 4:
						{return ops.unidadSemicolon();}
					case -6:
						break;
					case 5:
						{return ops.unidadLlaveA();}
					case -7:
						break;
					case 6:
						{return ops.unidadComa();}
					case -8:
						break;
					case 7:
						{return ops.unidadLlaveC();}
					case -9:
						break;
					case 8:
						{return ops.unidadAt();}
					case -10:
						break;
					case 9:
						{return ops.unidadIgual();}
					case -11:
						break;
					case 10:
						{errores.errorLexico(fila(),lexema());}
					case -12:
						break;
					case 11:
						{return ops.unidadLt();}
					case -13:
						break;
					case 12:
						{return ops.unidadGt();}
					case -14:
						break;
					case 13:
						{return ops.unidadSuma();}
					case -15:
						break;
					case 14:
						{return ops.unidadResta();}
					case -16:
						break;
					case 15:
						{return ops.unidadMul();}
					case -17:
						break;
					case 16:
						{return ops.unidadDiv();}
					case -18:
						break;
					case 17:
						{return ops.unidadPAp();}
					case -19:
						break;
					case 18:
						{return ops.unidadPCierre();}
					case -20:
						break;
					case 19:
						{return ops.unidadIf();}
					case -21:
						break;
					case 20:
						{return ops.unidadTo();}
					case -22:
						break;
					case 21:
						{return ops.unidadDo();}
					case -23:
						break;
					case 22:
						{return ops.unidadOr();}
					case -24:
						break;
					case 23:
						{return ops.unidadEq();}
					case -25:
						break;
					case 24:
						{return ops.unidadNeq();}
					case -26:
						break;
					case 25:
						{return ops.unidadLe();}
					case -27:
						break;
					case 26:
						{return ops.unidadGe();}
					case -28:
						break;
					case 27:
						{}
					case -29:
						break;
					case 28:
						{return ops.unidadAll();}
					case -30:
						break;
					case 29:
						{return ops.unidadInt();}
					case -31:
						break;
					case 30:
						{return ops.unidadAnd();}
					case -32:
						break;
					case 31:
						{return ops.unidadNot();}
					case -33:
						break;
					case 32:
						{return ops.unidadThen();}
					case -34:
						break;
					case 33:
						{return ops.unidadEnd();}
					case -35:
						break;
					case 34:
						{return ops.unidadElse();}
					case -36:
						break;
					case 35:
						{return ops.unidadCall();}
					case -37:
						break;
					case 36:
						{return ops.unidadWith();}
					case -38:
						break;
					case 37:
						{return ops.unidadBool();}
					case -39:
						break;
					case 38:
						{return ops.unidadTrue();}
					case -40:
						break;
					case 40:
						{return ops.unidadMakes();}
					case -41:
						break;
					case 41:
						{return ops.unidadDone();}
					case -42:
						break;
					case 42:
						{return ops.unidadWhile();}
					case -43:
						break;
					case 43:
						{return ops.unidadValue();}
					case -44:
						break;
					case 44:
						{return ops.unidadFalse();}
					case -45:
						break;
					case 45:
						{return ops.unidadMethod();}
					case -46:
						break;
					case 46:
						{return ops.unidadChoose();}
					case -47:
						break;
					case 47:
						{return ops.unidadChosen();}
					case -48:
						break;
					case 48:
						{return ops.unidadArrayOf();}
					case -49:
						break;
					case 49:
						{return ops.unidadReceiving();}
					case -50:
						break;
					case 50:
						{return ops.unidadMain();}
					case -51:
						break;
					case 51:
						{return ops.unidadInput();}
					case -52:
						break;
					case 52:
						{return ops.unidadConsidering();}
					case -53:
						break;
					case 53:
						{return ops.unidadReturn();}
					case -54:
						break;
					case 54:
						{return ops.unidadOutput();}
					case -55:
						break;
					case 55:
						{return ops.unidadEnt();}
					case -56:
						break;
					case 56:
						{return ops.unidadId();}
					case -57:
						break;
					case 57:
						{errores.errorLexico(fila(),lexema());}
					case -58:
						break;
					case 59:
						{return ops.unidadId();}
					case -59:
						break;
					case 61:
						{return ops.unidadId();}
					case -60:
						break;
					case 63:
						{return ops.unidadId();}
					case -61:
						break;
					case 65:
						{return ops.unidadId();}
					case -62:
						break;
					case 67:
						{return ops.unidadId();}
					case -63:
						break;
					case 69:
						{return ops.unidadId();}
					case -64:
						break;
					case 71:
						{return ops.unidadId();}
					case -65:
						break;
					case 73:
						{return ops.unidadId();}
					case -66:
						break;
					case 75:
						{return ops.unidadId();}
					case -67:
						break;
					case 77:
						{return ops.unidadId();}
					case -68:
						break;
					case 79:
						{return ops.unidadId();}
					case -69:
						break;
					case 81:
						{return ops.unidadId();}
					case -70:
						break;
					case 83:
						{return ops.unidadId();}
					case -71:
						break;
					case 85:
						{return ops.unidadId();}
					case -72:
						break;
					case 87:
						{return ops.unidadId();}
					case -73:
						break;
					case 89:
						{return ops.unidadId();}
					case -74:
						break;
					case 91:
						{return ops.unidadId();}
					case -75:
						break;
					case 93:
						{return ops.unidadId();}
					case -76:
						break;
					case 95:
						{return ops.unidadId();}
					case -77:
						break;
					case 97:
						{return ops.unidadId();}
					case -78:
						break;
					case 99:
						{return ops.unidadId();}
					case -79:
						break;
					case 101:
						{return ops.unidadId();}
					case -80:
						break;
					case 103:
						{return ops.unidadId();}
					case -81:
						break;
					case 104:
						{return ops.unidadId();}
					case -82:
						break;
					case 105:
						{return ops.unidadId();}
					case -83:
						break;
					case 106:
						{return ops.unidadId();}
					case -84:
						break;
					case 107:
						{return ops.unidadId();}
					case -85:
						break;
					case 108:
						{return ops.unidadId();}
					case -86:
						break;
					case 109:
						{return ops.unidadId();}
					case -87:
						break;
					case 110:
						{return ops.unidadId();}
					case -88:
						break;
					case 112:
						{return ops.unidadId();}
					case -89:
						break;
					case 113:
						{return ops.unidadId();}
					case -90:
						break;
					case 114:
						{return ops.unidadId();}
					case -91:
						break;
					case 115:
						{return ops.unidadId();}
					case -92:
						break;
					case 116:
						{return ops.unidadId();}
					case -93:
						break;
					case 117:
						{return ops.unidadId();}
					case -94:
						break;
					case 118:
						{return ops.unidadId();}
					case -95:
						break;
					case 119:
						{return ops.unidadId();}
					case -96:
						break;
					case 120:
						{return ops.unidadId();}
					case -97:
						break;
					case 121:
						{return ops.unidadId();}
					case -98:
						break;
					case 122:
						{return ops.unidadId();}
					case -99:
						break;
					case 123:
						{return ops.unidadId();}
					case -100:
						break;
					case 124:
						{return ops.unidadId();}
					case -101:
						break;
					case 125:
						{return ops.unidadId();}
					case -102:
						break;
					case 126:
						{return ops.unidadId();}
					case -103:
						break;
					case 127:
						{return ops.unidadId();}
					case -104:
						break;
					case 128:
						{return ops.unidadId();}
					case -105:
						break;
					case 129:
						{return ops.unidadId();}
					case -106:
						break;
					case 130:
						{return ops.unidadId();}
					case -107:
						break;
					case 131:
						{return ops.unidadId();}
					case -108:
						break;
					case 132:
						{return ops.unidadId();}
					case -109:
						break;
					case 133:
						{return ops.unidadId();}
					case -110:
						break;
					case 134:
						{return ops.unidadId();}
					case -111:
						break;
					case 135:
						{return ops.unidadId();}
					case -112:
						break;
					case 136:
						{return ops.unidadId();}
					case -113:
						break;
					case 137:
						{return ops.unidadId();}
					case -114:
						break;
					case 138:
						{return ops.unidadId();}
					case -115:
						break;
					case 139:
						{return ops.unidadId();}
					case -116:
						break;
					case 140:
						{return ops.unidadId();}
					case -117:
						break;
					case 141:
						{return ops.unidadId();}
					case -118:
						break;
					case 142:
						{return ops.unidadId();}
					case -119:
						break;
					case 143:
						{return ops.unidadId();}
					case -120:
						break;
					case 144:
						{return ops.unidadId();}
					case -121:
						break;
					case 145:
						{return ops.unidadId();}
					case -122:
						break;
					case 146:
						{return ops.unidadId();}
					case -123:
						break;
					case 147:
						{return ops.unidadId();}
					case -124:
						break;
					case 148:
						{return ops.unidadId();}
					case -125:
						break;
					case 149:
						{return ops.unidadId();}
					case -126:
						break;
					case 150:
						{return ops.unidadId();}
					case -127:
						break;
					case 151:
						{return ops.unidadId();}
					case -128:
						break;
					case 152:
						{return ops.unidadId();}
					case -129:
						break;
					case 153:
						{return ops.unidadId();}
					case -130:
						break;
					case 154:
						{return ops.unidadId();}
					case -131:
						break;
					case 155:
						{return ops.unidadId();}
					case -132:
						break;
					case 156:
						{return ops.unidadId();}
					case -133:
						break;
					case 157:
						{return ops.unidadId();}
					case -134:
						break;
					case 158:
						{return ops.unidadId();}
					case -135:
						break;
					case 159:
						{return ops.unidadId();}
					case -136:
						break;
					case 160:
						{return ops.unidadId();}
					case -137:
						break;
					case 161:
						{return ops.unidadId();}
					case -138:
						break;
					case 162:
						{return ops.unidadId();}
					case -139:
						break;
					case 163:
						{return ops.unidadId();}
					case -140:
						break;
					case 164:
						{return ops.unidadId();}
					case -141:
						break;
					case 165:
						{return ops.unidadId();}
					case -142:
						break;
					case 166:
						{return ops.unidadId();}
					case -143:
						break;
					case 167:
						{return ops.unidadId();}
					case -144:
						break;
					case 168:
						{return ops.unidadId();}
					case -145:
						break;
					case 169:
						{return ops.unidadId();}
					case -146:
						break;
					case 170:
						{return ops.unidadId();}
					case -147:
						break;
					case 171:
						{return ops.unidadId();}
					case -148:
						break;
					case 172:
						{return ops.unidadId();}
					case -149:
						break;
					case 173:
						{return ops.unidadId();}
					case -150:
						break;
					case 174:
						{return ops.unidadId();}
					case -151:
						break;
					case 175:
						{return ops.unidadId();}
					case -152:
						break;
					case 176:
						{return ops.unidadId();}
					case -153:
						break;
					case 177:
						{return ops.unidadId();}
					case -154:
						break;
					case 178:
						{return ops.unidadId();}
					case -155:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
