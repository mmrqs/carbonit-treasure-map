package fr.carbonit.writer;

import fr.carbonit.model.Adventurer;

public class AdventurerWriter extends AbstractWriter<Adventurer> {
    @Override
    public String write(Adventurer subject) {
        return String.join(" - ",
                Character.toString(subject.getType().getLabel()),
                subject.getName(),
                Integer.toString(subject.getCoordinates().getX()),
                Integer.toString(subject.getCoordinates().getY()),
                Character.toString(subject.getOrientation().getLabel()),
                Integer.toString(subject.getTreasureCount()));
    }
}
