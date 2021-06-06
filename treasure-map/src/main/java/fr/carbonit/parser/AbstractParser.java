package fr.carbonit.parser;

import fr.carbonit.model.TreasureMapObject;
import fr.carbonit.parser.exception.NumberOfArgumentException;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public abstract class AbstractParser<T extends TreasureMapObject> {
    protected static final String LINE_SEPARATOR = " - ";
    private final int numberOfParameters;

    public abstract T parse(@NonNull String itemLine) throws NumberOfArgumentException;

    protected String[] split(@NonNull String line) throws NumberOfArgumentException {
        String[] result = line.split(LINE_SEPARATOR);
        System.out.println(result);
        if(result.length != numberOfParameters)
            throw new NumberOfArgumentException(numberOfParameters, result.length);

        return result;
    }
}
