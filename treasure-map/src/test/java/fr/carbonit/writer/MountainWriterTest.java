package fr.carbonit.writer;

import fr.carbonit.model.Coordinates;
import fr.carbonit.model.Mountain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MountainWriterTest {
    private final MountainWriter writer = new MountainWriter();

    @Test
    void mountainWriterShouldReturnStringContainingInfos() {
        // given
        Mountain mountain = new Mountain(new Coordinates(2, 2));
        // when
        String result = writer.write(mountain);
        // then
        assertEquals(result, "M - 2 - 2");
    }

}