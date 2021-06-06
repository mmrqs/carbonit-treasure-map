package fr.carbonit.checker;

import fr.carbonit.checker.exception.IllegalDuplicateMountainPosition;
import fr.carbonit.checker.exception.IllegalPositionException;
import fr.carbonit.model.Board;
import fr.carbonit.model.Coordinates;
import fr.carbonit.model.Mountain;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MountainParserCheckerTest {

    private final Board board;
    private final MountainParserChecker checker;

    public MountainParserCheckerTest() {
        board = new Board(new Coordinates(3, 3));
        checker = new MountainParserChecker(board);
    }

    @Test
    void shouldReturnErrorIfMountainNotInTheMap() {
        // given
        Mountain mountain = new Mountain(new Coordinates(7, 7));
        // when
        List<Optional<GameConsistencyError>> test = checker.checkOrThrow(mountain);
        List<GameConsistencyError> errors = test.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        // then
        assert (errors.stream()
                .filter(obj -> obj.getError() instanceof IllegalPositionException)
                .collect(Collectors.toList()).size() == 1);
    }

    @Test
    void shouldReturnNullIfMountainInTheMap() {
        // given
        Mountain mountain = new Mountain(new Coordinates(1, 1));
        // when
        List<Optional<GameConsistencyError>> test = checker.checkOrThrow(mountain);
        List<GameConsistencyError> errors = test.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        // then
        assert (errors.stream()
                .filter(obj -> obj.getError() instanceof IllegalPositionException)
                .collect(Collectors.toList()).size() == 0);
    }

    @Test
    void shouldReturnErrorIfDuplicateMountain() {
        // given
        Mountain mountain = new Mountain(new Coordinates(1, 1));
        // when
        checker.checkOrThrow(mountain);
        List<Optional<GameConsistencyError>> test = checker.checkOrThrow(mountain);
        List<GameConsistencyError> errors = test.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        // then
        assert (errors.stream()
                .filter(obj -> obj.getError() instanceof IllegalDuplicateMountainPosition)
                .collect(Collectors.toList()).size() == 1);
    }

    @Test
    void shouldReturnNullIfNoDuplicateMountain() {
        // given
        Mountain mountain = new Mountain(new Coordinates(1, 1));
        // when
        List<Optional<GameConsistencyError>> test = checker.checkOrThrow(mountain);
        List<GameConsistencyError> errors = test.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        // then
        assert (errors.stream()
                .filter(obj -> obj.getError() instanceof IllegalDuplicateMountainPosition)
                .collect(Collectors.toList()).size() == 0);
    }
}