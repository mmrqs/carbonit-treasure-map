package fr.carbonit.game.action;

import fr.carbonit.game.TreasureMap;
import fr.carbonit.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoveForwardTest {

    private final MoveForward move = new MoveForward();
    private TreasureMap map;
    private Adventurer adventurer;

    @BeforeEach
    void init() {
        map = new TreasureMap(new Board(new Coordinates(5, 5)));
        adventurer = new Adventurer("Bob Moran",
                new Coordinates(2, 2),
                OrientationEnum.NORD,
                new LinkedList<>());
        map.addObject(adventurer);
    }

    @Test
    void shouldNotMoveIfCaseIsNotEmpty() {
        // given
        Mountain mountain = new Mountain(new Coordinates(2, 1));
        map.addObject(mountain);
        // when
        move.applyAction(adventurer, map);
        // then
        assertEquals(adventurer.getCoordinates(), new Coordinates(2, 2));
    }

    @Test
    void shouldMoveUpIfOrientationIsNorth() {
        // given
        // when
        move.applyAction(adventurer, map);
        // then
        List<Adventurer> result = map.getMap().stream()
                .filter(obj -> obj.getType().equals(TreasureMapObjectType.ADVENTURER))
                .map(o -> (Adventurer)o)
                .collect(Collectors.toList());
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getCoordinates(), new Coordinates(2, 1));
    }

    @Test
    void shouldMoveDownIfOrientationIsSouth() {
        // given
        adventurer.setOrientation(OrientationEnum.SUD);
        // when
        move.applyAction(adventurer, map);
        // then
        List<Adventurer> result = map.getMap().stream()
                .filter(obj -> obj.getType().equals(TreasureMapObjectType.ADVENTURER))
                .map(o -> (Adventurer)o)
                .collect(Collectors.toList());
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getCoordinates(), new Coordinates(2, 3));
    }

    @Test
    void shouldMoveRightIfOrientationIsEast() {
        // given
        adventurer.setOrientation(OrientationEnum.EST);
        // when
        move.applyAction(adventurer, map);
        // then
        List<Adventurer> result = map.getMap().stream()
                .filter(obj -> obj.getType().equals(TreasureMapObjectType.ADVENTURER))
                .map(o -> (Adventurer)o)
                .collect(Collectors.toList());
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getCoordinates(), new Coordinates(3, 2));
    }

    @Test
    void shouldMoveLeftIfOrientationIsWest() {
        // given
        adventurer.setOrientation(OrientationEnum.OUEST);
        // when
        move.applyAction(adventurer, map);
        // then
        List<Adventurer> result = map.getMap().stream()
                .filter(obj -> obj.getType().equals(TreasureMapObjectType.ADVENTURER))
                .map(o -> (Adventurer)o)
                .collect(Collectors.toList());
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getCoordinates(), new Coordinates(1, 2));
    }
}