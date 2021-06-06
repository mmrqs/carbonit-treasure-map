package fr.carbonit.checker.exception;

import fr.carbonit.model.TreasureMapObject;

public class IllegalDuplicateAdventurerNameException extends CheckException {
    public IllegalDuplicateAdventurerNameException(TreasureMapObject error) {
        super(error, IllegalDimensionsException.class);
    }
}