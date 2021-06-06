package fr.carbonit.checker;

import fr.carbonit.checker.exception.IllegalDimensionsException;
import fr.carbonit.checker.exception.IllegalNumberOfBoardsException;
import fr.carbonit.model.Board;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class BoardParserChecker extends AbstractParserChecker<Board> {

    private final List<Board> boards = new ArrayList<>();

    public GameConsistencyError isBoardDimensionsCorrect(@NonNull Board board) {
        return board.getCoordinates().getY() <= 0 || board.getCoordinates().getX() <= 0
                ? new GameConsistencyError(new IllegalDimensionsException(board))
                : null;
    }

    public GameConsistencyError isThereManyBoards(@NonNull Board board) {
        boards.add(board);
        return boards.size() != 1
                ? new GameConsistencyError(new IllegalNumberOfBoardsException())
                : null;
    }

    @Override
    public List<Optional<GameConsistencyError>> checkOrThrow(@NonNull Board subject) {
        return Arrays.asList(Optional.ofNullable(isBoardDimensionsCorrect(subject)),
                Optional.ofNullable(isThereManyBoards(subject)));
    }
}