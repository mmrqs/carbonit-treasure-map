package fr.carbonit.checker;

import fr.carbonit.checker.exception.IllegalDuplicateAdventurerNameException;
import fr.carbonit.checker.exception.IllegalPositionException;
import fr.carbonit.model.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AdventurerParserCheckerTest {
    private final AdventurerParserChecker checker;
    private final List<Mountain> mountains;
    private final Board board;

    public AdventurerParserCheckerTest() {
        board = new Board(new Coordinates(4, 4));
        mountains = Arrays.asList(new Mountain(new Coordinates(2, 3)));
        checker = new AdventurerParserChecker(mountains, board);
    }

    @Test
    void shouldReturnErrorIfAdventurerNotInTheMap() {
        // given
        Adventurer bobMoran = new Adventurer("Bob Moran",
                new Coordinates(400, 400),
                OrientationEnum.NORD,
                new LinkedList<>());
        // when
        List<Optional<GameConsistencyError>> test = checker.checkOrThrow(bobMoran);
        List<GameConsistencyError> errors = test.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        // then
        assert (errors.stream()
                .filter(obj -> obj.getError() instanceof IllegalPositionException)
                .collect(Collectors.toList()).size() == 1);
    }

    @Test
    void shouldReturnNullIfAdventurerIsInTheMap() {
        // given
        Adventurer bobMoran = new Adventurer("Bob Moran",
                new Coordinates(1, 1),
                OrientationEnum.NORD,
                new LinkedList<>());
        // when
        List<Optional<GameConsistencyError>> test = checker.checkOrThrow(bobMoran);
        List<GameConsistencyError> errors = test.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        // then
        assert (errors.stream()
                .filter(obj -> obj.getError() instanceof IllegalPositionException)
                .collect(Collectors.toList()).size() == 0);
    }

    @Test
    void ShouldReturnErrorIfAdventurerIsInOneMountain() {
        // given
        Adventurer bobMoran = new Adventurer("Bob Moran",
                new Coordinates(2, 3),
                OrientationEnum.NORD,
                new LinkedList<>());
        // when
        List<Optional<GameConsistencyError>> test = checker.checkOrThrow(bobMoran);
        List<GameConsistencyError> errors = test.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        // then
        assert (errors.stream()
                .filter(obj -> obj.getError() instanceof IllegalPositionException)
                .collect(Collectors.toList()).size() == 1);
    }

    @Test
    void ShouldReturnErrorIfTwoAdventurersHaveTheSameName() {
        // given
        Adventurer bobMoran = new Adventurer("Bob Moran",
                new Coordinates(2, 3),
                OrientationEnum.NORD,
                new LinkedList<>());
        // when
        checker.checkOrThrow(bobMoran);
        List<Optional<GameConsistencyError>> test = checker.checkOrThrow(bobMoran);
        List<GameConsistencyError> errors = test.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        // then
        assert (errors.stream()
                .filter(obj -> obj.getError() instanceof IllegalDuplicateAdventurerNameException)
                .collect(Collectors.toList()).size() == 1);
    }
}