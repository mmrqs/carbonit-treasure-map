package fr.carbonit.checker.exception;

import fr.carbonit.model.TreasureMapObject;

public class IllegalTreasureNumberException extends CheckException {
    public IllegalTreasureNumberException(TreasureMapObject error) {
        super(error, IllegalTreasureNumberException.class);
    }
}
