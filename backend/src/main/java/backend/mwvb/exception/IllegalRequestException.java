package backend.mwvb.exception;

public class IllegalRequestException extends RuntimeException {
    public IllegalRequestException(String msg) {
        super(msg);
    }
}
