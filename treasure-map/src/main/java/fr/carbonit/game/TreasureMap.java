package fr.carbonit.game;

import fr.carbonit.game.exception.UnremovableItemException;
import fr.carbonit.model.*;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TreasureMap {

    @Getter
    private final List<TreasureMapObject> map;
    @Getter
    private final Board board;

    public TreasureMap(@NonNull Board board) {
        this.board = board;
        map = new ArrayList<>();
    }

    public void addObject(@NonNull TreasureMapObject object) {
        List<TreasureMapObject> objectAlreadyThere = getObjectInMap(object.getCoordinates());
        if(object.getType().equals(TreasureMapObjectType.BOARD) || !isCaseAvailable(object.getCoordinates()))
            return;
        if(object.getType().equals(TreasureMapObjectType.ADVENTURER)
                && objectAlreadyThere.size() == 1 && objectAlreadyThere.get(0).getType().equals(TreasureMapObjectType.TREASURE))
            pickupTreasure((Adventurer)object, (Treasure)objectAlreadyThere.get(0));
        map.add(object);
    }

    public void removeObject(@NonNull TreasureMapObject object) {
        if(object.getType().equals(TreasureMapObjectType.MOUNTAIN) || object.getType().equals(TreasureMapObjectType.BOARD))
            throw new UnremovableItemException(object.getType());
        if(getObjectInMap(object.getCoordinates()).contains(object))
            map.remove(object);
    }

    private void pickupTreasure(@NonNull Adventurer adventurer, @NonNull Treasure treasure) {
        adventurer.pickupTreasure();
        treasure.collected();
        if(treasure.getQuantity() == 0)
            removeObject(treasure);
    }

    private List<TreasureMapObject> getObjectInMap(@NonNull Coordinates coordinates) {
        return map.stream()
                .filter(obj -> obj.getCoordinates().equals(coordinates))
                .collect(Collectors.toList());
    }

    public boolean isCaseAvailable(@NonNull Coordinates coordinates) {
        List<TreasureMapObject> result = getObjectInMap(coordinates);
        return (result.size() == 0 ||
                (result.size() == 1 && result.get(0).getType() == TreasureMapObjectType.TREASURE))
                && this.board.isObjectWithinBoard(coordinates);
    }
}
