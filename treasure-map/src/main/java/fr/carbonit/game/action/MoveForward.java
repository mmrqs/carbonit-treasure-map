package fr.carbonit.game.action;

import fr.carbonit.game.TreasureMap;
import fr.carbonit.model.Adventurer;
import fr.carbonit.model.Coordinates;
import lombok.NonNull;

public class MoveForward extends AbstractAction {

    @Override
    public void applyAction(@NonNull Adventurer subject, @NonNull TreasureMap map) {
        Coordinates nextLocation = getNextPosition(subject);

        if(map.isCaseAvailable(nextLocation)) {
            map.removeObject(subject);
            subject.setCoordinates(nextLocation);
            map.addObject(subject);
        }
    }

    private Coordinates getNextPosition(@NonNull Adventurer subject) {
        switch (subject.getOrientation()) {
            case NORD:
                return new Coordinates(subject.getCoordinates().getX(), subject.getCoordinates().getY() - 1);
            case SUD:
                return new Coordinates(subject.getCoordinates().getX(), subject.getCoordinates().getY() + 1);
            case OUEST:
                return new Coordinates(subject.getCoordinates().getX() - 1, subject.getCoordinates().getY());
            case EST:
                return new Coordinates(subject.getCoordinates().getX() + 1, subject.getCoordinates().getY());
            default :
                return null;
        }
    }
}
