package fr.carbonit.game.exception;

public abstract class GameException extends RuntimeException {

    public GameException(String message) {
        super(message);
    }

    public GameException() {
        super();
    }
}
