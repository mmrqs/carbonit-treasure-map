package fr.carbonit.exception;

public class ShouldNotHappenException extends RuntimeException {
    public ShouldNotHappenException() {
        super();
    }

    public ShouldNotHappenException(String message) {
        super(message);
    }

    public ShouldNotHappenException(Throwable cause) {
        super(cause);
    }
}

