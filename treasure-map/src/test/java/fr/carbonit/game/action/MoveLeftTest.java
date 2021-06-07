package fr.carbonit.game.action;

import fr.carbonit.game.TreasureMap;
import fr.carbonit.model.Adventurer;
import fr.carbonit.model.Board;
import fr.carbonit.model.Coordinates;
import fr.carbonit.model.OrientationEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class MoveLeftTest {

    private Adventurer adventurer;
    private TreasureMap map;
    private final MoveLeft move = new MoveLeft();

    @BeforeEach
    void init() {
        adventurer = new Adventurer("Bob Moran",
                new Coordinates(2, 2),
                OrientationEnum.NORD,
                new LinkedList<>());
        map = new TreasureMap(new Board(new Coordinates(5, 5)));
        map.addObject(adventurer);
    }

    @Test
    void shouldChangeOrientationToOuestIfNord() {
        // given
        adventurer.setOrientation(OrientationEnum.NORD);
        // when
        move.applyAction(adventurer, map);
        // then
        assertEquals(adventurer.getOrientation(), OrientationEnum.OUEST);
    }

    @Test
    void shouldChangeOrientationToEstIfSud() {
        // given
        adventurer.setOrientation(OrientationEnum.SUD);
        // when
        move.applyAction(adventurer, map);
        // then
        assertEquals(adventurer.getOrientation(), OrientationEnum.EST);
    }

    @Test
    void shouldChangeOrientationToNordIfEst() {
        // given
        adventurer.setOrientation(OrientationEnum.EST);
        // when
        move.applyAction(adventurer, map);
        // then
        assertEquals(adventurer.getOrientation(), OrientationEnum.NORD);
    }

    @Test
    void shouldChangeOrientationToOuestIfSud() {
        // given
        adventurer.setOrientation(OrientationEnum.OUEST);
        // when
        move.applyAction(adventurer, map);
        // then
        assertEquals(adventurer.getOrientation(), OrientationEnum.SUD);
    }
}