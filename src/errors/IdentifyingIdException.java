package src.errors;

public class IdentifyingIdException extends Exception {

	private static final long serialVersionUID = 1L;

	public IdentifyingIdException() {
        super();
    }

    public IdentifyingIdException(String message) {
        super(message);
    }

    public IdentifyingIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdentifyingIdException(Throwable cause) {
        super(cause);
    }
}
