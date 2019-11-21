package fileManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FileManager {
    private static Logger LOGGER = Logger.getLogger(FileManager.class.getName());
    private FilesWrapper files;

    public FileManager(FilesWrapper files) {
        this.files = files;
    }

    public void writeFile(Path path, String text) {
        try {
            files.write(path, text.getBytes());
        } catch (IOException error) {
            LOGGER.warning("Failed to write file: " + path + "\nError message: " + error + "\n");
        }
    }

    public List<String> readAllLines(Path path) {
        try {
            return files.readAllLines(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public File getTestDirectory(File directory) {
        if (directory.isDirectory()) {
            if (directory.getName().equals("test")) {
                return directory;
            } else {
                File[] files = directory.listFiles();

                if (files != null) {
                    for (File file : files) {
                        File nextFile = getTestDirectory(file);
                        if (nextFile != null && nextFile.isDirectory() && nextFile.getName().equals("test")) {
                            return nextFile;
                        }
                    }
                }
            }
        }

        return null;
    }

    public void ensureDirectoryExists(Path directory) {
        try {
            files.createDirectories(directory);
        } catch (IOException error) {
            LOGGER.warning("Failed to write directory: " + directory + "\nError message: " + error + "\n");
        }
    }
}
