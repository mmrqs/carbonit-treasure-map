package fr.carbonit.writer;

import fr.carbonit.model.TreasureMapObject;
import lombok.NonNull;

public abstract class AbstractWriter<T extends TreasureMapObject> {
    public @NonNull String write(T subject) {
        return String.join(" - ",
                Character.toString(subject.getType().getLabel()),
                Integer.toString(subject.getCoordinates().getX()),
                Integer.toString(subject.getCoordinates().getY()));
    }
}
