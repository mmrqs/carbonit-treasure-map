package fr.carbonit.game.exception;

import fr.carbonit.model.TreasureMapObjectType;
import lombok.NonNull;

public class UnremovableItemException extends GameException{
    private static final String ERROR_MESSAGE = "A %s item can't be removed !";

    public UnremovableItemException(
            @NonNull TreasureMapObjectType type) {
        super(String.format(ERROR_MESSAGE, type));
    }
}
