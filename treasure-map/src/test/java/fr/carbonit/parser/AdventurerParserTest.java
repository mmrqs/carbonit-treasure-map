package fr.carbonit.parser;

import fr.carbonit.model.Adventurer;
import fr.carbonit.model.Coordinates;
import fr.carbonit.parser.exception.NumberOfArgumentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdventurerParserTest {
    private final AdventurerParser parser;

    public AdventurerParserTest() {
        parser = new AdventurerParser();
    }

    @Test
    void parseShouldThrowNumberOfArgumentExceptionIfNotEnoughArgument() {
        // given
        String wrongNumberOfArgument = "A - Lara - 1 - 1";
        // when
        // then
        Assertions.assertThrows(
                NumberOfArgumentException.class,
                () -> parser.parse(wrongNumberOfArgument));
    }

    @Test
    void parseShouldThrowNumberOfArgumentExceptionIfTooMuchArgument() {
        // given
        String wrongNumberOfArgument = "A - Lara - 1 - 1 - S - AADADAGGA - test";
        // when
        // then
        Assertions.assertThrows(
                NumberOfArgumentException.class,
                () -> parser.parse(wrongNumberOfArgument));
    }

    @Test
    void parseShouldReturnAdventurerObject() throws NumberOfArgumentException {
        // given
        String correctLine = "A - Lara - 1 - 1 - S - AADADAGGA";
        // when
        Adventurer adventurer = parser.parse(correctLine);
        // then
        assertNotNull(adventurer);
        assertEquals(adventurer.getName(), "Lara");
        assertEquals(adventurer.getCoordinates(), new Coordinates(1, 1));
    }
}