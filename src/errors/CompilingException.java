package errors;

public class CompilingException extends Exception {

	private static final long serialVersionUID = 1L;

	public CompilingException() {
        super();
    }

    public CompilingException(String message) {
        super(message);
    }

    public CompilingException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompilingException(Throwable cause) {
        super(cause);
    }
}
