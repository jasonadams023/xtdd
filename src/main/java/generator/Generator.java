package generator;

import projectStructure.classObjects.classRequirement.ClassRequirement;
import parsing.testParser.TestParser;
import fileManager.FileManager;
import projectStructure.classObjects.javaClass.JavaClass;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Generator {
    private File directory;
    private FileManager fileManager;
    private List<JavaClass> javaClasses;
    private List<ClassRequirement> classRequirements;

    public Generator(File directory, FileManager fileManager) {
        this.directory = directory;
        this.fileManager = fileManager;
        this.javaClasses = new ArrayList<>();
        this.classRequirements = new ArrayList<>();
    }

    public void generate() {
        getRequirementsFromTestFiles();
        passRequirements();
        writeFiles();
    }

    private void getRequirementsFromTestFiles() {
        TestParser testParser = new TestParser(fileManager);

        for (File testFile : getTestFiles()) {
            classRequirements = testParser.parseTestFile(testFile.toPath());
        }
    }

    private void passRequirements() {
        for (ClassRequirement classRequirement : classRequirements) {
            addClass(classRequirement.name);
            passRequirementToClass(classRequirement);
        }
    }

    private void addClass(String className) {
        if (javaClasses.stream().noneMatch(o -> o.getName().equals(className))) {
            javaClasses.add(new JavaClass(className));
        }
    }

    private void passRequirementToClass(ClassRequirement classRequirement) {
        for (JavaClass javaClass : javaClasses) {
            if (javaClass.getName().equals(classRequirement.name)) {
                javaClass.addRequirement(classRequirement.function);
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
