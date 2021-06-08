package fr.carbonit.writer;

import fr.carbonit.model.Treasure;

public class TreasureWriter extends AbstractWriter<Treasure> {
    @Override
    public String write(Treasure subject) {
        return String.join(" - ",
                Character.toString(subject.getType().getLabel()),
                Integer.toString(subject.getCoordinates().getX()),
                Integer.toString(subject.getCoordinates().getY()),
                Integer.toString(subject.getQuantity()));
    }
}
