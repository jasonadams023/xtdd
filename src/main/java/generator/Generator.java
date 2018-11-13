package generator;

import Requirement.Requirement;
import TestParser.TestParser;
import fileManager.FileManager;
import javaClass.JavaClass;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Generator {
    private List<JavaClass> javaClasses;
    private FileManager fileManager;
    private File directory;

    public Generator(File directory, FileManager fileManager) {
        this.directory = directory;
        this.fileManager = fileManager;
        this.javaClasses = new ArrayList<>();
    }

    public void generate() {
        readFiles();
        writeFiles();
    }

    private void readFiles() {
        File testDirectory = fileManager.getTestDirectory(directory);
        File[] testFiles = testDirectory.listFiles();

        TestParser testParser = new TestParser(fileManager);
        List<Requirement> requirements = new ArrayList<>();

        for (File testFile : testFiles) {
            requirements = testParser.parseTestFile(testFile.toPath());
        }

        for (Requirement requirement : requirements) {
            addClass(requirement.className);

            for (JavaClass javaClass : javaClasses) {
                if (javaClass.getName().equals(requirement.className)) {
                    javaClass.addRequirement(requirement.function);
                }
            }
        }
    }

    private void writeFiles() {
        for (JavaClass javaClass : javaClasses) {
            Path path = Paths.get(directory.getPath() + "/src/" + javaClass.getName() + ".java");
            fileManager.writeFile(path, javaClass.toString());
        }
    }

    private void addClass(String className) {
        if (javaClasses.stream().noneMatch(o -> o.getName().equals(className))) {
            javaClasses.add(new JavaClass(className));
        }
    }
}
