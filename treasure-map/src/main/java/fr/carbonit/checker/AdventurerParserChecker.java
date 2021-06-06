package fr.carbonit.checker;

import fr.carbonit.checker.exception.IllegalDuplicateAdventurerNameException;
import fr.carbonit.checker.exception.IllegalPositionException;
import fr.carbonit.model.Adventurer;
import fr.carbonit.model.Board;
import fr.carbonit.model.Mountain;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class AdventurerParserChecker extends AbstractParserChecker<Adventurer> {

    private final List<String> names = new ArrayList<>();

    @NonNull private final List<Mountain> mountains;
    @NonNull private final Board board;
    
    private GameConsistencyError isAdventurerInTheMap(@NonNull Adventurer adventurer) {
        return !board.isObjectWithinBoard(adventurer.getCoordinates())
                ? new GameConsistencyError(new IllegalPositionException(adventurer))
                : null;
    }

    private GameConsistencyError isAdventurerInMountain(@NonNull Adventurer adventurer) {
        return mountains.stream()
                .filter(obj -> obj.getCoordinates().equals(adventurer.getCoordinates()))
                .count() != 0
                ? new GameConsistencyError(new IllegalPositionException(adventurer))
                : null;
    }

    private GameConsistencyError isThereAdventurersWithSameName(@NonNull Adventurer adventurer) {
        names.add(adventurer.getName());

        return names.stream()
                .filter(name -> name.equals(adventurer.getName()))
                .count() != 1
                ? new GameConsistencyError(new IllegalDuplicateAdventurerNameException(adventurer))
                : null;
    }

    @Override
    public List<Optional<GameConsistencyError>> checkOrThrow(@NonNull Adventurer subject) {
        return Arrays.asList(Optional.ofNullable(isAdventurerInTheMap(subject)),
                Optional.ofNullable(isAdventurerInMountain(subject)),
                Optional.ofNullable(isThereAdventurersWithSameName(subject)));
    }
}