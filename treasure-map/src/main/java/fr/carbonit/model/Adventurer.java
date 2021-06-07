package fr.carbonit.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Queue;

@Getter
public class Adventurer extends TreasureMapObject {
    @NonNull final String name;
    @NonNull @Setter private OrientationEnum orientation;
    @NonNull private Queue<MovementEnum> moves;
    @NonNull private int treasureCount;

    public Adventurer(
            @NonNull String name,
            @NonNull Coordinates coordinates,
            @NonNull OrientationEnum orientation,
            @NonNull Queue<MovementEnum> moves) {
        super(coordinates, TreasureMapObjectType.ADVENTURER);
        this.name = name;
        this.orientation = orientation;
        this.moves = moves;
        this.treasureCount = 0;
    }

    public void pickupTreasure() {
        this.treasureCount++;
    }
}
