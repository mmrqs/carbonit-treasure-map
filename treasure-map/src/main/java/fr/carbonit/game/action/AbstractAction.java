package fr.carbonit.game.action;

import fr.carbonit.game.TreasureMap;
import fr.carbonit.model.Adventurer;
import lombok.NonNull;

public abstract class AbstractAction {
    public abstract void applyAction(@NonNull Adventurer subject, @NonNull TreasureMap map);
}
