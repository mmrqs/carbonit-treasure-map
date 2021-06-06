package fr.carbonit.checker.exception;

import fr.carbonit.model.TreasureMapObject;

public class IllegalDuplicateMountainPosition extends CheckException {
    public IllegalDuplicateMountainPosition(TreasureMapObject error) {
        super(error, IllegalDimensionsException.class);
    }
}
