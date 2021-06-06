package fr.carbonit.checker;

import fr.carbonit.model.TreasureMapObject;

import java.util.List;
import java.util.Optional;

public abstract class AbstractParserChecker<T extends TreasureMapObject> {
    public abstract List<Optional<GameConsistencyError>> checkOrThrow(T subject);
}
