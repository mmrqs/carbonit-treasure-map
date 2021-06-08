package fr.carbonit.checker.exception;

import fr.carbonit.checker.GameConsistencyError;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

public class IllegalEntryFileDataException extends CheckException {
    public IllegalEntryFileDataException(@NonNull List<GameConsistencyError> violations) {
        super(violations.stream().map(Object::toString).collect(Collectors.joining(",")));
    }
}
