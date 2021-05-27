package fr.carbonit.model;

import lombok.NonNull;
import lombok.Setter;

public abstract class TreasureMapObject {
    @NonNull @Setter private final Coordinates coordinates;

    public TreasureMapObject(@NonNull Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
