package fr.carbonit.parser;

import fr.carbonit.exception.ShouldNotHappenException;
import fr.carbonit.model.*;
import fr.carbonit.parser.exception.NumberOfArgumentException;
import fr.carbonit.parser.exception.ParserException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.UncheckedIOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GameParserTest {

    private final GameParser parser;

    public GameParserTest() {
        parser = new GameParser();
    }

    @Test
    void shouldThrowUncheckedIOExceptionIfFileNotFound() {
        // given
        String wrongPath = "imawrongpath";
        // when
        // then
        Assertions.assertThrows(
                UncheckedIOException.class,
                () -> parser.parseFile(wrongPath));
    }

    @Test
    void shouldParseFile() throws ParserException {
        // given
        String path = "src/test/java/fr/carbonit/test";
        // when
        List<TreasureMapObject> result = parser.parseFile(path);
        Board boardResult = (Board) result.stream()
                .filter(obj -> obj.getType().equals(TreasureMapObjectType.BOARD))
                .findFirst()
                .orElseThrow(ShouldNotHappenException::new);
        Mountain mountainResult = (Mountain) result.stream()
                .filter(obj -> obj.getType().equals(TreasureMapObjectType.MOUNTAIN))
                .findFirst()
                .orElseThrow(ShouldNotHappenException::new);
        Treasure treasureResult = (Treasure) result.stream()
                .filter(obj -> obj.getType().equals(TreasureMapObjectType.TREASURE))
                .findFirst()
                .orElseThrow(ShouldNotHappenException::new);
        Adventurer adventurerResult = (Adventurer) result.stream()
                .filter(obj -> obj.getType().equals(TreasureMapObjectType.ADVENTURER))
                .findFirst()
                .orElseThrow(ShouldNotHappenException::new);
        // then
        assertNotNull(result);
        assertEquals(result.size(), 4);
        assertEquals(boardResult.getCoordinates(), new Coordinates(3, 4));
        assertEquals(mountainResult.getCoordinates(), new Coordinates(1, 0));
        assertEquals(treasureResult.getCoordinates(), new Coordinates(0, 3));
        assertEquals(treasureResult.getQuantity(), 2);
        assertEquals(adventurerResult.getName(), "Lara");
        assertEquals(adventurerResult.getCoordinates(), new Coordinates(1, 1));
    }
}