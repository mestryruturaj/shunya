package io.two.bit.saint.shunya.exception;

public class InvalidArgumentException extends RuntimeException {

    public InvalidArgumentException() {
        super();
    }

    public InvalidArgumentException(String s) {
        super(s);
    }

    public InvalidArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidArgumentException(Throwable cause) {
        super(cause);
    }

    @java.io.Serial
    private static final long serialVersionUID = -5360630128856068164L;
}
