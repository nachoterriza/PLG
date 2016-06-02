package src.errors;

public class UnsuportedOperation extends Exception {

	private static final long serialVersionUID = 1L;

	public UnsuportedOperation() {
        super();
    }

    public UnsuportedOperation(String message) {
        super(message);
    }

    public UnsuportedOperation(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsuportedOperation(Throwable cause) {
        super(cause);
    }
}
