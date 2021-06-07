package fr.carbonit.game.action;

import fr.carbonit.game.TreasureMap;
import fr.carbonit.model.Adventurer;
import fr.carbonit.model.OrientationEnum;
import lombok.NonNull;

public class MoveRight extends AbstractAction {
    @Override
    public void applyAction(@NonNull Adventurer subject, @NonNull TreasureMap map) {
        subject.setOrientation(getNextOrientation(subject));
    }

    private OrientationEnum getNextOrientation(@NonNull Adventurer adventurer) {
        switch (adventurer.getOrientation()) {
            case NORD:
                return OrientationEnum.EST;
            case SUD:
                return OrientationEnum.OUEST;
            case EST:
                return OrientationEnum.SUD;
            case OUEST:
                return OrientationEnum.NORD;
            default:
                return null;
        }
    }
}
