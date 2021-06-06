package fr.carbonit.checker;

import fr.carbonit.checker.exception.CheckException;
import fr.carbonit.checker.exception.IllegalEntryFileDataException;
import fr.carbonit.checker.exception.IllegalNumberOfBoardsException;
import fr.carbonit.model.Board;
import fr.carbonit.model.Mountain;
import fr.carbonit.model.TreasureMapObject;
import fr.carbonit.model.TreasureMapObjectType;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class GameParserChecker {
    @NonNull
    private final Map<TreasureMapObjectType, AbstractParserChecker> checkers;
    @NonNull
    private List<GameConsistencyError> exceptions;

    @NonNull private final List<TreasureMapObject> gameObjects;

    public GameParserChecker(@NonNull List<TreasureMapObject> objects) throws CheckException {
        this.gameObjects = objects;

        Board board = this.gameObjects.stream()
                .filter(object -> object instanceof Board)
                .map(b -> (Board)b)
                .findFirst()
                .orElseThrow(IllegalNumberOfBoardsException::new);

        List<Mountain> mountains = this.gameObjects.stream()
                .filter(object -> object instanceof Mountain)
                .map(m -> (Mountain)m)
                .collect(Collectors.toList());

        checkers = Map.of(
                TreasureMapObjectType.TREASURE, new TreasureParserChecker(board),
                TreasureMapObjectType.ADVENTURER, new AdventurerParserChecker(mountains, board),
                TreasureMapObjectType.BOARD, new BoardParserChecker(),
                TreasureMapObjectType.MOUNTAIN, new MountainParserChecker(board));

        exceptions = new ArrayList<>();
    }

    public void checkData() throws IllegalEntryFileDataException {
        this.gameObjects.forEach(obj -> {
            List<Optional<GameConsistencyError>> errors = checkers.get(obj.getType()).checkOrThrow(obj);

            exceptions.addAll(errors.stream()
                    .flatMap(Optional::stream)
                    .collect(Collectors.toList()));
        });

        if(!exceptions.isEmpty())
            throw new IllegalEntryFileDataException(exceptions);
    }
}
