package fileManager.model;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface Model {

    public List<Path> getPaths();

    public void setPaths(List<Path> paths);

    public void setPaths(String path) throws IOException;
}
