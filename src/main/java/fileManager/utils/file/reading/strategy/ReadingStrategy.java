package fileManager.utils.file.reading.strategy;

import java.io.IOException;
import java.nio.file.Path;

public interface ReadingStrategy {
    public String readFile(Path path) throws IOException;
}
