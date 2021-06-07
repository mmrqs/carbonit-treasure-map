package fr.carbonit.game.action;

import fr.carbonit.game.TreasureMap;
import fr.carbonit.model.Adventurer;
import fr.carbonit.model.OrientationEnum;
import lombok.NonNull;

public class MoveLeft extends AbstractAction {

    @Override
    public void applyAction(@NonNull Adventurer subject, @NonNull TreasureMap map) {
        subject.setOrientation(getNextOrientation(subject));
    }

    private OrientationEnum getNextOrientation(@NonNull Adventurer adventurer) {
        switch (adventurer.getOrientation()) {
            case NORD:
                return OrientationEnum.OUEST;
            case SUD:
                return OrientationEnum.EST;
            case EST:
                return OrientationEnum.NORD;
            case OUEST:
                return OrientationEnum.SUD;
            default:
                return null;
        }
    }
}
