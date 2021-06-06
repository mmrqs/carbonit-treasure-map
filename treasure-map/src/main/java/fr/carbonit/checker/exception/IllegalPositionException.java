package fr.carbonit.checker.exception;

import fr.carbonit.model.TreasureMapObject;

public class IllegalPositionException extends CheckException {
    public IllegalPositionException(TreasureMapObject error) {
        super(error, IllegalPositionException.class);
    }
}
