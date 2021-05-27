package fr.carbonit.model;

import lombok.Getter;
import lombok.NonNull;

public class Treasure extends TreasureMapObject {
    @NonNull private int quantity;

    public Treasure(
            @NonNull Coordinates coordinates,
            @NonNull int nb) {
        super(coordinates);
        this.quantity = nb;
    }
}
