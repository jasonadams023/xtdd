package generator;

import filesWrapper.FilesWrapper;
import javaClass.FileReader;
import javaClass.JavaClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Generator {
    File directory;
    FilesWrapper files;

    public Generator(File targetDirectory) {
        directory = targetDirectory;
    }

    public Generator(File directory, FilesWrapper filesWrapper) {
        this.directory = directory;
        this.files = filesWrapper;
    }

    public void generate() {
        File testDirectory = files.getTestDirectory(directory);
        File[] testFiles = testDirectory.listFiles();

        for (File testFile : testFiles) {
            FileReader reader = (path) -> files.readAllLines(path);

            JavaClass javaClass = new JavaClass(testFile, reader);
            javaClass.readFile();

            String className = javaClass.getName();
            Path path = Paths.get(directory.getPath() + "/src/" + className + ".java");

            files.writeFile(path, javaClass.toString());
        }
    }

    //Will become dependant on ProjectDirectory structure
    void writeFile(Path targetPath, String input) {
        byte[] outputBytes = input.getBytes();

        try {
            Files.write(targetPath, outputBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
