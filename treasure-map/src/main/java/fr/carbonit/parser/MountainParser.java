package fr.carbonit.parser;

import fr.carbonit.model.Coordinates;
import fr.carbonit.model.Mountain;
import fr.carbonit.parser.exception.NumberOfArgumentException;
import lombok.NonNull;

public class MountainParser extends AbstractParser<Mountain> {
    private static final int NUMBER_OF_PARAMETERS = 3;

    public MountainParser() {
        super(NUMBER_OF_PARAMETERS);
    }

    @Override
    public Mountain parse(@NonNull String itemLine) throws NumberOfArgumentException {
        String[] characteristics = split(itemLine);
        return new Mountain(new Coordinates(Integer.parseInt(characteristics[1]), Integer.parseInt(characteristics[2])));
    }
}
