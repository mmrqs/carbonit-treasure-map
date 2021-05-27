package fr.carbonit.model;

import fr.carbonit.exception.ShouldNotHappenException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
public enum MovementEnum {
    AVANCER('A'),
    TOURNER_GAUCHE('G'),
    TOURNER_DROITE('D');

    @Getter
    private final char label;

    public static MovementEnum get(char letter) {
        return Arrays.asList(MovementEnum.values())
                .stream().filter(el -> el.getLabel() == letter)
                .findFirst()
                .orElseThrow(ShouldNotHappenException::new);
    }
}
