package fileManager.utils.file.reading.strategy;

import fileManager.utils.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.StringJoiner;

public class TxtReadingStrategy implements ReadingStrategy {
    @Override
    public String readFile(Path path) throws IOException {
        if (!Files.isDirectory(path) && FileUtils.getFileExtension(path).equals("txt")) {
            StringJoiner stringJoiner = new StringJoiner(System.getProperty("line.separator"));//разделяет присоединяемые строчки
            Files.readAllLines(path).forEach(stringJoiner::add);

            return stringJoiner.toString();
        }

        throw new IllegalArgumentException("Неправильный файл");
    }
}
