package errors;

public class IdentifyingIdException extends Exception {

	private static final long serialVersionUID = 1L;
	private int filaDecl;
	private int fila;

	public IdentifyingIdException() {
        super();
    }

    public IdentifyingIdException(String message) {
        super(message);
        this.fila = -1;
        this.filaDecl = -1;
    }
    
    public IdentifyingIdException(String message, int fila, int filaDecl) {
        super(message);
        this.fila = fila;
        this.filaDecl = filaDecl;
    }

    public IdentifyingIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdentifyingIdException(Throwable cause) {
        super(cause);
    }

	public int getFila() {
		return fila;
	}

	public int getFilaDecl() {
		return filaDecl;
	}

}
