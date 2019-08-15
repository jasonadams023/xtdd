package fileManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private FilesWrapper files;

    public FileManager(FilesWrapper files) {
        this.files = files;
    }

    public void writeFile(Path path, String text) {
        try {
            files.write(path, text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
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
        File[] directoryFiles = directory.listFiles();
        if (directoryFiles != null) {
            for (File file : directoryFiles) {
                if (file.getName().equals("test") && file.isDirectory()) {
                    return file;
                }
            }
        }

        return null;
    }
}
