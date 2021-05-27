package fr.carbonit.parser.exception;

public class ParserException extends Exception {
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
