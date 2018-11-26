package fileManager.utils.file.reading.strategy;

import java.io.IOException;
import java.nio.file.Path;

public class DllReadingStrategy implements ReadingStrategy {
    @Override
    public String readFile(Path path) throws IOException {
        return "В dll какая-то хуита";
    }
}
