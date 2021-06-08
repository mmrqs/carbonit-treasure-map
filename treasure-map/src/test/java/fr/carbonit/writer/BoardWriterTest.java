package fr.carbonit.writer;

import fr.carbonit.model.Board;
import fr.carbonit.model.Coordinates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardWriterTest {
    private final BoardWriter writer = new BoardWriter();

    @Test
    void boardWriterShouldReturnStringContainingInfos() {
        // given
        Board board = new Board(new Coordinates(2, 2));
        // when
        String result = writer.write(board);
        // then
        assertEquals(result, "C - 2 - 2");
    }
}