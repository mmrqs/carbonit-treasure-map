package fr.carbonit.model;

import fr.carbonit.exception.ShouldNotHappenException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
public enum TreasureMapObjectType {
    BOARD('C'),
    TREASURE('T'),
    ADVENTURER('A'),
    MOUNTAIN('M');

    @Getter
    private final char label;

    public static TreasureMapObjectType get(char letter) {
        return Arrays.asList(TreasureMapObjectType.values())
                .stream().filter(el -> el.getLabel() == letter)
                .findFirst()
                .orElseThrow(ShouldNotHappenException::new);
    }
}
