package fr.carbonit.parser;

import fr.carbonit.model.Coordinates;
import fr.carbonit.model.Treasure;
import fr.carbonit.parser.exception.NumberOfArgumentException;
import lombok.NonNull;

public class TreasureParser extends AbstractParser<Treasure> {
    private static final int NUMBER_OF_PARAMETERS = 4;

    public TreasureParser() {
        super(NUMBER_OF_PARAMETERS);
    }

    @Override
    public Treasure parse(@NonNull String itemLine) throws NumberOfArgumentException {
        String[] characteristics = split(itemLine);
        return new Treasure(new Coordinates(Integer.parseInt(characteristics[1]), Integer.parseInt(characteristics[2])),
                Integer.parseInt(characteristics[3]));
    }
}
