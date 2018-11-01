package generator;

import fileManager.FileManager;
import javaClass.JavaClass;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Generator {
    File directory;
    FileManager files;

    public Generator(File directory, FileManager fileManager) {
        this.directory = directory;
        this.files = fileManager;
    }

    public void generate() {
        File testDirectory = files.getTestDirectory(directory);
        File[] testFiles = testDirectory.listFiles();

        for (File testFile : testFiles) {
            JavaClass javaClass = new JavaClass(testFile, files);
            javaClass.readFile();

            String className = javaClass.getName();
            Path path = Paths.get(directory.getPath() + "/src/" + className + ".java");

            files.writeFile(path, javaClass.toString());
        }
    }
}
