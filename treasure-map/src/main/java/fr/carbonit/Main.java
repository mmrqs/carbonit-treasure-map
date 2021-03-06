package fr.carbonit;

import fr.carbonit.checker.GameParserChecker;
import fr.carbonit.checker.exception.CheckException;
import fr.carbonit.game.GameManager;
import fr.carbonit.game.TreasureMap;
import fr.carbonit.model.TreasureMapObject;
import fr.carbonit.parser.GameParser;
import fr.carbonit.parser.exception.ParserException;
import fr.carbonit.writer.GameWriter;
import lombok.NonNull;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static final String FILE_NAME = "result_" + LocalDateTime.now();
    public static String input;

    public static void main(@NonNull String[] args) {
        input = args[0];
        try {
            List<TreasureMapObject> components = new GameParser().parseFile(input);

            GameParserChecker checker = new GameParserChecker(components);
            checker.checkData();

            GameManager gameManager = new GameManager(components);
            TreasureMap map = gameManager.playGame();

            GameWriter writer = new GameWriter(map.getMap(), map.getBoard());
            writer.writeOutputFile(FILE_NAME);
        } catch (ParserException | CheckException | IOException e) {
            e.printStackTrace();
        }
    }
}
