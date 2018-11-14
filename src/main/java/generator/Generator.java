package generator;

import requirement.Requirement;
import TestParser.TestParser;
import fileManager.FileManager;
import projectStructure.javaClass.JavaClass;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Generator {
    private File directory;
    private FileManager fileManager;
    private List<JavaClass> javaClasses;
    private List<Requirement> requirements;

    public Generator(File directory, FileManager fileManager) {
        this.directory = directory;
        this.fileManager = fileManager;
        this.javaClasses = new ArrayList<>();
        this.requirements = new ArrayList<>();
    }

    public void generate() {
        getRequirementsFromTestFiles();
        passRequirements();
        writeFiles();
    }

    private void getRequirementsFromTestFiles() {
        TestParser testParser = new TestParser(fileManager);

        for (File testFile : getTestFiles()) {
            requirements = testParser.parseTestFile(testFile.toPath());
        }
    }

    private void passRequirements() {
        for (Requirement requirement : requirements) {
            addClass(requirement.className);
            passRequirementToClass(requirement);
        }
    }

    private void addClass(String className) {
        if (javaClasses.stream().noneMatch(o -> o.getName().equals(className))) {
            javaClasses.add(new JavaClass(className));
        }
    }

    private void passRequirementToClass(Requirement requirement) {
        for (JavaClass javaClass : javaClasses) {
            if (javaClass.getName().equals(requirement.className)) {
                javaClass.addRequirement(requirement.function);
            }
        }
    }

    private File[] getTestFiles() {
        File testDirectory = fileManager.getTestDirectory(directory);
        return testDirectory.listFiles();
    }

    private void writeFiles() {
        for (JavaClass javaClass : javaClasses) {
            Path path = Paths.get(directory.getPath() + "/src/" + javaClass.getName() + ".java");
            fileManager.writeFile(path, javaClass.toString());
        }
    }
}
