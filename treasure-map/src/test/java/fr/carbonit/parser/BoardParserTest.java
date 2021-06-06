package fr.carbonit.parser;

import fr.carbonit.model.Board;
import fr.carbonit.model.Coordinates;
import fr.carbonit.parser.exception.NumberOfArgumentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BoardParserTest {
    private final BoardParser parser;

    public BoardParserTest() {
        parser = new BoardParser();
    }

    @Test
    void parseShouldThrowNumberOfArgumentExceptionIfNotEnoughArgument() {
        // given
        String wrongNumberOfArgument = "C - 3";
        // when
        // then
        Assertions.assertThrows(
                NumberOfArgumentException.class,
                () -> parser.parse(wrongNumberOfArgument));
    }

    @Test
    void parseShouldThrowNumberOfArgumentExceptionIfTooMuchArgument() {
        // given
        String wrongNumberOfArgument = "C - 3 - 4 - 5";
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
        Board board = parser.parse(correctLine);
        // then
        assertNotNull(board);
        assertEquals(board.getCoordinates(), new Coordinates(3, 4));
    }

}