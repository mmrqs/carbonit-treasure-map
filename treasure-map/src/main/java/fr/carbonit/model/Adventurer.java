package fr.carbonit.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Queue;

public class Adventurer extends TreasureMapObject {
    @NonNull @Getter final String name;
    @NonNull @Setter private OrientationEnum orientation;
    @NonNull private final Queue<MovementEnum> moves;

    public Adventurer(
            @NonNull String name,
            @NonNull Coordinates coordinates,
            @NonNull OrientationEnum orientation,
            @NonNull Queue<MovementEnum> moves) {
        super(coordinates, TreasureMapObjectType.ADVENTURER);
        this.name = name;
        this.orientation = orientation;
        this.moves = moves;
    }
}
