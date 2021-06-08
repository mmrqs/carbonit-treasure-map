package fr.carbonit.writer;

import fr.carbonit.model.Board;
import fr.carbonit.model.TreasureMapObject;
import fr.carbonit.model.TreasureMapObjectType;
import lombok.NonNull;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class GameWriter {
    @NonNull private final Map<TreasureMapObjectType, AbstractWriter> writers;
    @NonNull private final List<TreasureMapObject> gameObjects;
    @NonNull private final Board board;

    public GameWriter(@NonNull List<TreasureMapObject> gameObjects, @NonNull Board board) {
        this.gameObjects = gameObjects;
        this.writers = Map.of(
                TreasureMapObjectType.ADVENTURER, new AdventurerWriter(),
                TreasureMapObjectType.TREASURE, new TreasureWriter(),
                TreasureMapObjectType.BOARD, new BoardWriter(),
                TreasureMapObjectType.MOUNTAIN, new MountainWriter());
        this.board = board;
    }

    public void writeOutputFile(@NonNull String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        BufferedWriter writer = new BufferedWriter(fileWriter);

        writer.write(writers.get(TreasureMapObjectType.BOARD).write(this.board));
        writer.newLine();

        for (TreasureMapObject obj : this.gameObjects) {
            writer.write(writers.get(obj.getType()).write(obj));
            writer.newLine();
        }

        writer.close();
        fileWriter.close();
    }
}
