package fr.carbonit.checker.exception;

import fr.carbonit.model.TreasureMapObject;

public class IllegalDimensionsException extends CheckException {
    public IllegalDimensionsException(TreasureMapObject error) {
        super(error, IllegalDimensionsException.class);
    }
}
