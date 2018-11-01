package generator;

import filesWrapper.FilesWrapper;
import javaClass.JavaClass;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Generator {
    File directory;
    FilesWrapper files;

    public Generator(File directory, FilesWrapper filesWrapper) {
        this.directory = directory;
        this.files = filesWrapper;
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
