package fr.carbonit.checker;

import fr.carbonit.checker.exception.IllegalDuplicateMountainPosition;
import fr.carbonit.checker.exception.IllegalPositionException;
import fr.carbonit.model.Board;
import fr.carbonit.model.Coordinates;
import fr.carbonit.model.Mountain;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class MountainParserChecker extends AbstractParserChecker<Mountain> {

    @NonNull private final Board board;
    private final List<Coordinates> coordinates = new ArrayList<>();

    public GameConsistencyError isMountainInTheMap(Mountain mountain) {
        return !board.isObjectWithinBoard(mountain.getCoordinates())
            ? new GameConsistencyError(new IllegalPositionException(mountain))
            : null;
    }

    public GameConsistencyError isThereMountainsWithSamePosition(@NonNull Mountain mountain) {
        coordinates.add(mountain.getCoordinates());

        return coordinates.stream()
                .filter(c -> c.equals(mountain.getCoordinates()))
                .count() != 1
                ? new GameConsistencyError(new IllegalDuplicateMountainPosition(mountain))
                : null;
    }

    @Override
    public List<Optional<GameConsistencyError>> checkOrThrow(Mountain subject) {
        return Arrays.asList(Optional.ofNullable(isMountainInTheMap(subject)),
                Optional.ofNullable(isThereMountainsWithSamePosition(subject)));
    }
}