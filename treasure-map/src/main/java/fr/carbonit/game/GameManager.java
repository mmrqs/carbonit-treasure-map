package fr.carbonit.game;

import fr.carbonit.exception.ShouldNotHappenException;
import fr.carbonit.game.action.AbstractAction;
import fr.carbonit.game.action.MoveForward;
import fr.carbonit.game.action.MoveLeft;
import fr.carbonit.game.action.MoveRight;
import fr.carbonit.model.*;
import lombok.NonNull;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameManager {

    @NonNull private final List<TreasureMapObject> objects;
    @NonNull private final TreasureMap treasureMap;
    @NonNull private final Map<MovementEnum, AbstractAction> actions;

    public GameManager(@NonNull List<TreasureMapObject> objects) {
        this.objects = objects;
        this.treasureMap = new TreasureMap((Board)objects.stream()
                .filter(obj -> obj.getType().equals(TreasureMapObjectType.BOARD))
                .findFirst()
                .orElseThrow(ShouldNotHappenException::new));
        objects.forEach(treasureMap::addObject);
        this.actions = Map.of(
                MovementEnum.AVANCER, new MoveForward(),
                MovementEnum.TOURNER_DROITE, new MoveRight(),
                MovementEnum.TOURNER_GAUCHE, new MoveLeft());
    }

    public TreasureMap playGame() {
        List<Adventurer> remainingAdventurers = getRemainingAdventurers();
        while(remainingAdventurers.size() != 0) {
            for(Adventurer adventurer : remainingAdventurers) {
                actions.get(adventurer.getMoves().poll()).applyAction(adventurer, treasureMap);
                remainingAdventurers = getRemainingAdventurers();
            }
        }
        return treasureMap;
    }

    private List<Adventurer> getRemainingAdventurers() {
        return objects.stream()
                .filter(obj -> obj.getType().equals(TreasureMapObjectType.ADVENTURER))
                .map(o -> (Adventurer)o)
                .filter(a -> !a.getMoves().isEmpty())
                .collect(Collectors.toList());
    }
}
