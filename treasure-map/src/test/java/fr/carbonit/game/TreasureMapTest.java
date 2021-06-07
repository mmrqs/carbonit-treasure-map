package fr.carbonit.game;

import fr.carbonit.game.exception.UnremovableItemException;
import fr.carbonit.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class TreasureMapTest {

    private TreasureMap treasureMap;

    @BeforeEach
    void init() {
        treasureMap = new TreasureMap(new Board(new Coordinates(5, 5)));
    }

    @Test
    void addObjectShouldNotAddIfTheObjectIsBoard() {
        // given
        Board board = new Board(new Coordinates(3, 3));
        // when
        treasureMap.addObject(board);
        // then
        assertEquals(treasureMap.getMap().contains(board), false);
    }

    @Test
    void addObjectShouldNotAddIfCaseOccupied() {
        // given
        treasureMap.addObject(new Mountain(new Coordinates(3, 3)));
        // when
        treasureMap.addObject(new Mountain(new Coordinates(3, 3)));
        // then
        assertEquals(treasureMap.getMap().size(), 1);

    }

    @Test
    void addObjectShouldNotAddIfCaseOutOfBounds() {
        // when
        treasureMap.addObject(new Mountain(new Coordinates(300, 300)));
        // then
        assertEquals(treasureMap.getMap().size(), 0);
    }

    @Test
    void adventurerShouldPickupTreasureIfPresent() {
        // given
        Adventurer adventurer = new Adventurer("Bob Moran",
                new Coordinates(2, 2),
                OrientationEnum.NORD,
                new LinkedList<>());
        Treasure treasure = new Treasure(new Coordinates(2, 2), 3);
        // when
        treasureMap.addObject(treasure);
        treasureMap.addObject(adventurer);
        // then
        Treasure newTreasure = (Treasure)(treasureMap.getMap().stream()
                .filter(obj -> obj.getType().equals(TreasureMapObjectType.TREASURE))
                .findFirst()
                .orElseThrow());
        assertEquals(newTreasure.getQuantity(), 2);
    }

    @Test
    void addObjectShouldAddObject() {
        // given
        Mountain mountain = new Mountain(new Coordinates(2, 2));
        // when
        treasureMap.addObject(mountain);
        // then
        Mountain newMountain = (Mountain)(treasureMap.getMap().stream()
                .filter(obj -> obj.getType().equals(TreasureMapObjectType.MOUNTAIN))
                .findFirst()
                .orElseThrow());
        assertEquals(treasureMap.getMap().size(), 1);
        assertEquals(mountain, newMountain);
    }

    @Test
    void shouldDeleteTreasureIfEntirelyCollected() {
        // given
        Adventurer adventurer = new Adventurer("Bob Moran",
                new Coordinates(2, 2),
                OrientationEnum.NORD,
                new LinkedList<>());
        Treasure treasure = new Treasure(new Coordinates(2, 2), 1);
        // when
        treasureMap.addObject(treasure);
        treasureMap.addObject(adventurer);
        // then
        assertFalse(treasureMap.getMap().contains(treasure));
    }

    @Test
    void shouldThrowExceptionIfTryToRemoveUnremovableObject() {
        // given
        Board unremovableObject = new Board(new Coordinates(5, 5));
        // when
        // then
        Assertions.assertThrows(
                UnremovableItemException.class,
                () -> treasureMap.removeObject(unremovableObject));

    }

    @Test
    void shouldRemoveObject() {
        // given
        Treasure treasure = new Treasure(new Coordinates(2, 2), 0);
        // when
        treasureMap.addObject(treasure);
        treasureMap.removeObject(treasure);
        // then
        assertFalse(treasureMap.getMap().contains(treasure));
    }

    @Test
    void shouldReturnTrueIfCaseIsEmpty() {
        // when
        boolean result = treasureMap.isCaseAvailable(new Coordinates(1, 1));
        // then
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueIfCaseIsOccupiedByTreasure() {
        // given
        treasureMap.addObject(new Treasure(new Coordinates(2, 2), 1));
        // when
        boolean result = treasureMap.isCaseAvailable(new Coordinates(2, 2));
        // then
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfCaseOccupied() {
        // given
        treasureMap.addObject(new Mountain(new Coordinates(2, 2)));
        // when
        boolean result = treasureMap.isCaseAvailable(new Coordinates(2, 2));
        // then
        assertFalse(result);
    }

}