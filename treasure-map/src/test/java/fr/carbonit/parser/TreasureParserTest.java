package fr.carbonit.parser;

import fr.carbonit.model.Coordinates;
import fr.carbonit.model.Mountain;
import fr.carbonit.model.Treasure;
import fr.carbonit.model.TreasureMapObjectType;
import fr.carbonit.parser.exception.NumberOfArgumentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreasureParserTest {

    private final TreasureParser parser;

    public TreasureParserTest() {
        parser = new TreasureParser();
    }

    @Test
    void parseShouldThrowNumberOfArgumentExceptionIfNotEnoughArgument() {
        // given
        String wrongNumberOfArgument = "T - 2";
        // when
        // then
        Assertions.assertThrows(
                NumberOfArgumentException.class,
                () -> parser.parse(wrongNumberOfArgument));
    }

    @Test
    void parseShouldThrowNumberOfArgumentExceptionIfTooMuchArgument() {
        // given
        String wrongNumberOfArgument = "T - 3 - 4 - 5 - 5";
        // when
        // then
        Assertions.assertThrows(
                NumberOfArgumentException.class,
                () -> parser.parse(wrongNumberOfArgument));
    }

    @Test
    void parseShouldReturnAdventurerObject() throws NumberOfArgumentException {
        // given
        String correctLine = "T - 3 - 4 - 5";
        // when
        Treasure treasure = parser.parse(correctLine);
        // then
        assertNotNull(treasure);
        assertEquals(treasure.getCoordinates(), new Coordinates(3, 4));
        assertEquals(treasure.getQuantity(), 5);
        assertEquals(treasure.getType(), TreasureMapObjectType.TREASURE);
    }

}