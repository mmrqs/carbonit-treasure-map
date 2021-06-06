package fr.carbonit.model;

import lombok.NonNull;

public class Board extends TreasureMapObject {

    public Board(@NonNull Coordinates dimensions) {
        super(dimensions, TreasureMapObjectType.BOARD);
    }

    public boolean isObjectWithinBoard(Coordinates coordinates) {
        return coordinates.getX() >= 0 &&
                coordinates.getX() <= this.getCoordinates().getX() &&
                coordinates.getY() >= 0 &&
                coordinates.getY() <= this.getCoordinates().getY();
    }
}
