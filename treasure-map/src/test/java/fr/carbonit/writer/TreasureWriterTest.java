package fr.carbonit.writer;

import fr.carbonit.model.Coordinates;
import fr.carbonit.model.Treasure;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreasureWriterTest {

    private final TreasureWriter writer = new TreasureWriter();

    @Test
    void treasureWriterShouldReturnStringContainingInfos() {
        // given
        Treasure treasure = new Treasure(new Coordinates(2, 2), 2);
        // when
        String result = writer.write(treasure);
        // then
        assertEquals(result, "T - 2 - 2 - 2");
    }
}