package fr.carbonit.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
public abstract class TreasureMapObject {
    @NonNull @Setter private Coordinates coordinates;
    @NonNull private final TreasureMapObjectType type;

    public TreasureMapObject(
            @NonNull Coordinates coordinates,
            @NonNull TreasureMapObjectType type) {
        this.coordinates = coordinates;
        this.type = type;
    }
}
