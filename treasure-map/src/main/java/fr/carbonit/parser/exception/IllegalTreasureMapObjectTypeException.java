package fr.carbonit.parser.exception;

import lombok.NonNull;

public class IllegalTreasureMapObjectTypeException extends ParserException {
    private static final String ERROR_MESSAGE = "Illegal Treasure map object type entered : %c";

    public IllegalTreasureMapObjectTypeException(
            @NonNull char illegalType) {
        super(String.format(ERROR_MESSAGE, illegalType));
    }
}
