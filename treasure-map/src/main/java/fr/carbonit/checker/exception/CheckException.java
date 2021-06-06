package fr.carbonit.checker.exception;

public abstract class CheckException extends Exception {
    public <T> CheckException(T data, Class<? extends CheckException> cause) {
        super(String.format("Incorrect data %s : %s.", data.toString(), cause.getSimpleName()));
    }

    public CheckException(Class<? extends CheckException> cause) {
        super(String.format("Incorrect data entered : %s.", cause.getSimpleName()));
    }

    public CheckException(String message) {
        super(message);
    }
}

