package fileManager.utils;

import fileManager.utils.file.reading.strategy.ReadingStrategy;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static List<Path> getDirectoryPath(String path) throws IOException {
        List<Path> pathList = new ArrayList<>();
        Path dir = FileSystems.getDefault().getPath(path);
        Files.newDirectoryStream(dir).forEach(pathList::add);

        return pathList;
    }

    /**
     * узнаем есть ли в файле фраза, удаляя переносы строк
     * @param path файлик
     * @param sequence чо ищем
     * @return нашли или чо
     * @throws IOException не прочиталося((
     */
    public static boolean doesFileContainSequence(Path path, String sequence) throws IOException {
        return getReadingStrategy(path)
                .readFile(path)
                .replaceAll(System.getProperty("line.separator"), "")
                .contains(sequence);
    }

    /**
     * полчаем имя файла и вызываем перегрузку для стринга
     * @param path файл
     * @return расширение
     */
    public static String getFileExtension(Path path){
        return getFileExtension(path.getFileName().toString());
    }

    public static String getFileExtension(String path){
        return FilenameUtils.getExtension(path);
    }

    /**
     * берет енум на пол шишечки
     * @param path название файла
     * @return возвращаем стратегию по чтению файла
     */
    public static ReadingStrategy getReadingStrategy(String path) {
        return FileExtension.fromString(path).getReadingStrategy();
    }

    public static ReadingStrategy getReadingStrategy(Path path) {
        return getReadingStrategy(path.getFileName().toString());
    }


}
