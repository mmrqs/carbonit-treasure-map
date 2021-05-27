package fr.carbonit.model;

import lombok.NonNull;

public class Board extends TreasureMapObject {

    public Board(@NonNull Coordinates dimensions) {
        super(dimensions);
    }
}
