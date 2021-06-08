package fr.carbonit.writer;

import fr.carbonit.model.Adventurer;
import fr.carbonit.model.Coordinates;
import fr.carbonit.model.OrientationEnum;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class AdventurerWriterTest {

    private final AdventurerWriter writer = new AdventurerWriter();

    @Test
    void adventurerWriterShouldReturnStringContainingInfos() {
        // given
        Adventurer adventurer = new Adventurer("Bob Moran",
                new Coordinates(2, 2),
                OrientationEnum.NORD,
                new LinkedList<>());
        // when
        String result = writer.write(adventurer);
        // then
        assertEquals(result, "A - Bob Moran - 2 - 2 - N - 0");
    }
}