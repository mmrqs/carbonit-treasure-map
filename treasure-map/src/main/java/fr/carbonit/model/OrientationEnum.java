package fr.carbonit.model;

import fr.carbonit.exception.ShouldNotHappenException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;


@AllArgsConstructor
public enum OrientationEnum {
    NORD('N'),
    SUD('S'),
    EST('E'),
    OUEST('O');

    @Getter
    private final char label;

    public static OrientationEnum get(char letter) {
        return Arrays.asList(OrientationEnum.values())
                .stream().filter(el -> el.getLabel() == Character.toUpperCase(letter))
                .findFirst()
                .orElseThrow(ShouldNotHappenException::new);
    }
}
