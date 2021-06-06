package fr.carbonit.checker;

import fr.carbonit.checker.exception.IllegalDimensionsException;
import fr.carbonit.checker.exception.IllegalNumberOfBoardsException;
import fr.carbonit.model.Board;
import fr.carbonit.model.Coordinates;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BoardParserCheckerTest {

    public final BoardParserChecker checker;

    public BoardParserCheckerTest() {
        checker = new BoardParserChecker();
    }

    @Test
    void shouldReturnErrorIfBoardDimensionsAreNotCorrect() {
        // given
        Board board = new Board(new Coordinates(-3, 4));
        // when
        List<Optional<GameConsistencyError>> test = checker.checkOrThrow(board);
        List<GameConsistencyError> errors = test.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        // then
        assert (errors.stream()
                .filter(obj -> obj.getError() instanceof IllegalDimensionsException)
                .collect(Collectors.toList()).size() == 1);
    }

    @Test
    void shouldReturnNullIfBoardDimensionsAreCorrect() {
        // given
        Board board = new Board(new Coordinates(3, 4));
        // when
        List<Optional<GameConsistencyError>> test = checker.checkOrThrow(board);
        List<GameConsistencyError> errors = test.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        // then
        assert (errors.stream()
                .filter(obj -> obj.getError() instanceof IllegalDimensionsException)
                .collect(Collectors.toList()).size() == 0);
    }

    @Test
    void shouldReturnErrorIfManyBoards() {
        // given
        Board board = new Board(new Coordinates(3, 4));
        // when
        checker.checkOrThrow(board);
        List<Optional<GameConsistencyError>> test = checker.checkOrThrow(board);
        List<GameConsistencyError> errors = test.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        // then
        assert (errors.stream()
                .filter(obj -> obj.getError() instanceof IllegalNumberOfBoardsException)
                .collect(Collectors.toList()).size() == 1);
    }

    @Test
    void shouldReturnNullIfNumberOfBoardCorrect() {
        // given
        Board board = new Board(new Coordinates(3, 4));
        // when
        List<Optional<GameConsistencyError>> test = checker.checkOrThrow(board);
        List<GameConsistencyError> errors = test.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        // then
        assert (errors.stream()
                .filter(obj -> obj.getError() instanceof IllegalNumberOfBoardsException)
                .collect(Collectors.toList()).size() == 0);
    }

}