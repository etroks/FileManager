package fileManager.model;

import fileManager.utils.FileUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class FrameData implements Model{
    private List<Path> paths;

    public FrameData(String path) throws IOException {
        this.paths = FileUtils.getDirectoryPath(path);
    }

    public List<Path> getPaths() {
        return paths;
    }

    public void setPaths(List<Path> paths) {
        this.paths = paths;
    }

    public void setPaths(String path) throws IOException {
        this.paths = FileUtils.getDirectoryPath(path);
    }


}
