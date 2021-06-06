package fr.carbonit.parser;

import fr.carbonit.model.Coordinates;
import fr.carbonit.model.Mountain;
import fr.carbonit.parser.exception.NumberOfArgumentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MountainParserTest {

    private final MountainParser parser;

    public MountainParserTest() {
        parser = new MountainParser();
    }

    @Test
    void parseShouldThrowNumberOfArgumentExceptionIfNotEnoughArgument() {
        // given
        String wrongNumberOfArgument = "M - 2";
        // when
        // then
        Assertions.assertThrows(
                NumberOfArgumentException.class,
                () -> parser.parse(wrongNumberOfArgument));
    }

    @Test
    void parseShouldThrowNumberOfArgumentExceptionIfTooMuchArgument() {
        // given
        String wrongNumberOfArgument = "M - 3 - 4 - 5";
        // when
        // then
        Assertions.assertThrows(
                NumberOfArgumentException.class,
                () -> parser.parse(wrongNumberOfArgument));
    }

    @Test
    void parseShouldReturnAdventurerObject() throws NumberOfArgumentException {
        // given
        String correctLine = "C - 3 - 4";
        // when
        Mountain mountain = parser.parse(correctLine);
        // then
        assertNotNull(mountain);
        assertEquals(mountain.getCoordinates(), new Coordinates(3, 4));
    }
}