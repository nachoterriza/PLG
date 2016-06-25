package errors;

public class TypeMismatchException extends Exception {

	private static final long serialVersionUID = 1L;

	public TypeMismatchException() {
        super();
    }

    public TypeMismatchException(String message) {
        super(message);
    }
    
    public TypeMismatchException(String message, int fila) {
        super(message);
        this.fila = fila;
    }

    public TypeMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public TypeMismatchException(Throwable cause) {
        super(cause);
    }
    
    private int fila;
}
