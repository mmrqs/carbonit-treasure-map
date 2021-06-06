package fr.carbonit.checker;

import fr.carbonit.checker.exception.IllegalPositionException;
import fr.carbonit.checker.exception.IllegalTreasureNumberException;
import fr.carbonit.model.Board;
import fr.carbonit.model.Coordinates;
import fr.carbonit.model.Mountain;
import fr.carbonit.model.Treasure;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class TreasureParserCheckerTest {
    private final Board board;
    private final TreasureParserChecker checker;

    public TreasureParserCheckerTest() {
        board = new Board(new Coordinates(3, 3));
        checker = new TreasureParserChecker(board);
    }

    @Test
    void shouldReturnErrorIfTreasureNotInTheMap() {
        // given
        Treasure treasure = new Treasure(new Coordinates(7, 7), 1);
        // when
        List<Optional<GameConsistencyError>> test = checker.checkOrThrow(treasure);
        List<GameConsistencyError> errors = test.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        // then
        assert (errors.stream()
                .filter(obj -> obj.getError() instanceof IllegalPositionException)
                .collect(Collectors.toList()).size() == 1);
    }

    @Test
    void shouldReturnNullIfTreasureInTheMap() {
        // given
        Treasure treasure = new Treasure(new Coordinates(1, 1), 1);
        // when
        List<Optional<GameConsistencyError>> test = checker.checkOrThrow(treasure);
        List<GameConsistencyError> errors = test.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        // then
        assert (errors.stream()
                .filter(obj -> obj.getError() instanceof IllegalPositionException)
                .collect(Collectors.toList()).size() == 0);
    }

    @Test
    void shouldReturnErrorIfIllegalNumberOfTreasure() {
        // given
        Treasure treasure = new Treasure(new Coordinates(1, 1), -1);
        // when
        List<Optional<GameConsistencyError>> test = checker.checkOrThrow(treasure);
        List<GameConsistencyError> errors = test.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        // then
        assert (errors.stream()
                .filter(obj -> obj.getError() instanceof IllegalTreasureNumberException)
                .collect(Collectors.toList()).size() == 1);
    }

    @Test
    void shouldReturnNullIfCorrectNumberOfTreasure() {
        // given
        Treasure treasure = new Treasure(new Coordinates(1, 1), 1);
        // when
        List<Optional<GameConsistencyError>> test = checker.checkOrThrow(treasure);
        List<GameConsistencyError> errors = test.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        // then
        assert (errors.stream()
                .filter(obj -> obj.getError() instanceof IllegalTreasureNumberException)
                .collect(Collectors.toList()).size() == 0);
    }
}