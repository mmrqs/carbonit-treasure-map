package fr.carbonit.parser;

import fr.carbonit.model.Board;
import fr.carbonit.model.Coordinates;
import fr.carbonit.parser.exception.NumberOfArgumentException;
import lombok.NonNull;

public class BoardParser extends AbstractParser<Board> {
    private static final int NUMBER_OF_PARAMETERS = 3;

    public BoardParser() {
        super(NUMBER_OF_PARAMETERS);
    }

    @Override
    public Board parse(@NonNull String itemLine) throws NumberOfArgumentException {
        String[] characteristics = split(itemLine);
        return new Board(new Coordinates(Integer.parseInt(characteristics[1]), Integer.parseInt(characteristics[2])));
    }
}
