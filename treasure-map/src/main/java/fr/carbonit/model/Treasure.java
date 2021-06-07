package fr.carbonit.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class Treasure extends TreasureMapObject {
    @NonNull private int quantity;

    public Treasure(
            @NonNull Coordinates coordinates,
            @NonNull int nb) {
        super(coordinates, TreasureMapObjectType.TREASURE);
        this.quantity = nb;
    }

    public void collected() {
        quantity--;
    }
}
