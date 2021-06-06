package fr.carbonit.parser.exception;

public abstract class ParserException extends Exception {
    public ParserException(String message) {
        super(message);
    }

    public ParserException() {
        super();
    }

    public ParserException(Throwable cause) {
        super(cause);
    }
}
