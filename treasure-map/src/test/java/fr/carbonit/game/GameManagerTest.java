package fr.carbonit.game;

import fr.carbonit.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameManagerTest {

    @Test
    void playGameShouldExecuteMovementsOfAdventurer() {
        // given
        Adventurer adventurer = new Adventurer("Bob Moran",
                new Coordinates(2, 2),
                OrientationEnum.NORD,
                new LinkedList<>(Arrays.asList(MovementEnum.AVANCER, MovementEnum.TOURNER_DROITE)));
        Board board = new Board(new Coordinates(6, 6));
        GameManager manager = new GameManager(new ArrayList<>(Arrays.asList(adventurer, board)));
        // when
        TreasureMap map = manager.playGame();
        // then
        Adventurer adventurer1 = (Adventurer)map.getMap().get(0);
        assertEquals(adventurer1.getCoordinates(), new Coordinates(2, 1));
        assertEquals(adventurer1.getOrientation(), OrientationEnum.EST);
    }
}