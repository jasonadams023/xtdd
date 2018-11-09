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
import java.util.regex.Pattern;

public class Generator {
    List<JavaClass> javaClasses;
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

        for (File testFile : testFiles) {
            createClasses(testFile.toPath());
        }

        for (File testFile : testFiles) {
            populateClasses(testFile.toPath());
        }
    }

    private void writeFiles() {
        for (JavaClass javaClass : javaClasses) {
            Path path = Paths.get(directory.getPath() + "/src/" + javaClass.getName() + ".java");
            fileManager.writeFile(path, javaClass.toString());
        }
    }

    private void createClasses(Path path) {
        TestParser testParser = new TestParser(fileManager);
        List<Requirement> requirements = testParser.parseTestFile(path);

        for (Requirement requirement : requirements) {
            addClass(requirement.className);
        }
    }

    String getClassNameFromImport(String line) {
        String[] split = line.split(Pattern.quote("."));
        String last = split[split.length - 1];
        String className = last.split(Pattern.quote(";"))[0];

        if (className.contains(" ") || className.equals("")) {
            return null;
        }

        return className;
    }

    void addClass(String className) {
        if (className != null && javaClasses.stream().noneMatch(o -> o.getName().equals(className))) {
            javaClasses.add(new JavaClass(className, fileManager));
        }
    }

    private void populateClasses(Path path) {
        for (JavaClass javaClass : javaClasses) {
            javaClass.createFunctionsFromPath(path);
        }
    }
}
