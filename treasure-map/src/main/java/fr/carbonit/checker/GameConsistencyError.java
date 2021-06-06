package fr.carbonit.checker;

import fr.carbonit.checker.exception.CheckException;
import lombok.Data;
import lombok.NonNull;

@Data
public class GameConsistencyError {
    @NonNull private final CheckException error;

    @Override
    public String toString() {
        return String.format("Violation caused by : %s", error);
    }
}
