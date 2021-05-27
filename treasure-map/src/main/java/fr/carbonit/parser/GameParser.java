package fr.carbonit.parser;

import fr.carbonit.model.TreasureMapObject;
import fr.carbonit.model.TreasureMapObjectType;
import fr.carbonit.parser.exception.ParserException;
import lombok.NonNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameParser {

    @NonNull private final Map<TreasureMapObjectType, GenericParser> parsers;

    public GameParser() {
        parsers = Map.of(
                TreasureMapObjectType.TREASURE, new TreasureParser(),
                TreasureMapObjectType.ADVENTURER, new AdventurerParser(),
                TreasureMapObjectType.BOARD, new BoardParser(),
                TreasureMapObjectType.MOUNTAIN, new MountainParser());
    }

    public List<TreasureMapObject> parseFile(@NonNull String path) throws ParserException {
        List<TreasureMapObject> result = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();

            while (line != null) {
                if(parsers.containsKey(TreasureMapObjectType.get(line.charAt(0))))
                    result.add(parsers.get(TreasureMapObjectType.get(line.charAt(0))).parse(line));
                line = reader.readLine();
            }

            return result;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } finally {
            try {
                if(reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
