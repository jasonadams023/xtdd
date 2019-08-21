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
}
