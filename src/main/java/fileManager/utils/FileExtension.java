package fileManager.utils;

import fileManager.utils.file.reading.strategy.DllReadingStrategy;
import fileManager.utils.file.reading.strategy.ReadingStrategy;
import fileManager.utils.file.reading.strategy.TxtReadingStrategy;

public enum FileExtension {
    TXT("txt", new TxtReadingStrategy()),
    DLL("dll", new DllReadingStrategy());

    private String extension;
    private ReadingStrategy readingStrategy;

    FileExtension(String extension, ReadingStrategy readingStrategy) {
        this.extension = extension;
        this.readingStrategy = readingStrategy;
    }

    public String getExtension() {
        return extension;
    }

    public ReadingStrategy getReadingStrategy() {
        return readingStrategy;
    }

    /**
     *
     * @param fileName название файла
     * @return енум с расширением и стратой чтения
     */
    public static FileExtension fromString(String fileName) {
        for (FileExtension extensionEnum : FileExtension.values()) {
            if (extensionEnum.getExtension().equals(FileUtils.getFileExtension(fileName))) {
                return extensionEnum;
            }
        }

        throw new IllegalArgumentException("Нвозможно прочитать данный файл.");
    }
}
