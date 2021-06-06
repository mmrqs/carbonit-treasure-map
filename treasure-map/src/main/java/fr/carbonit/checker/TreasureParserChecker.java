package fr.carbonit.checker;

import fr.carbonit.checker.exception.IllegalPositionException;
import fr.carbonit.checker.exception.IllegalTreasureNumberException;
import fr.carbonit.model.Board;
import fr.carbonit.model.Treasure;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class TreasureParserChecker extends AbstractParserChecker<Treasure> {
    @NonNull private final Board board;

    public GameConsistencyError isTreasureInTheMap(Treasure treasure) {
        return !board.isObjectWithinBoard(treasure.getCoordinates())
                ? new GameConsistencyError(new IllegalPositionException(treasure))
                : null;
    }

    public GameConsistencyError isTreasureNumberCorrect(Treasure treasure) {
        return treasure.getQuantity() < 0
                ? new GameConsistencyError(new IllegalTreasureNumberException(treasure))
                : null;
    }

    @Override
    public List<Optional<GameConsistencyError>> checkOrThrow(Treasure subject) {
        return Arrays.asList(Optional.ofNullable(isTreasureInTheMap(subject)),
                Optional.ofNullable(isTreasureNumberCorrect(subject)));
    }
}
