package fr.carbonit.parser;

import fr.carbonit.model.Adventurer;
import fr.carbonit.model.Coordinates;
import fr.carbonit.model.MovementEnum;
import fr.carbonit.model.OrientationEnum;
import fr.carbonit.parser.exception.NumberOfArgumentException;
import lombok.NonNull;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class AdventurerParser extends GenericParser<Adventurer> {
    private static final int NUMBER_OF_PARAMETERS = 6;

    public AdventurerParser() {
        super(NUMBER_OF_PARAMETERS);
    }

    @Override
    public Adventurer parse(@NonNull String itemLine) throws NumberOfArgumentException {
        String[] characteristics = split(itemLine);
        return new Adventurer(characteristics[1],
                new Coordinates(Integer.parseInt(characteristics[2]), Integer.parseInt(characteristics[3])),
                OrientationEnum.get(characteristics[4].charAt(0)),
                parseMovements(characteristics[5]));
    }

    private Queue<MovementEnum> parseMovements(@NonNull String movements) {
        return movements.chars()
                .mapToObj(c -> (char)c)
                .map(c -> MovementEnum.get(c))
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
