package generator;

import projectStructure.classObjects.classRequirement.ClassRequirement;
import parsing.testFile.TestFileParser;
import fileManager.FileManager;
import projectStructure.classObjects.javaClass.JavaClass;
import projectStructure.functionObjects.functionRequirement.FunctionRequirement;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Generator {
    private FileManager fileManager;
    private List<JavaClass> javaClasses;
    private List<ClassRequirement> classRequirements;

    public Generator(FileManager fileManager) {
        this.fileManager = fileManager;
        this.javaClasses = new ArrayList<>();
        this.classRequirements = new ArrayList<>();
    }

    public void generate(File projectDirectory) {
        getRequirementsFromTestFiles(projectDirectory);
        passRequirements();
        writeFiles(projectDirectory);
    }

    private void getRequirementsFromTestFiles(File projectDirectory) {
        TestFileParser testFileParser = new TestFileParser(fileManager);

        for (File testFile : getTestFiles(projectDirectory)) {
            classRequirements = testFileParser.parseTestFile(testFile.toPath());
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
                for (FunctionRequirement functionRequirement : classRequirement.functionRequirements) {
                    javaClass.addRequirement(functionRequirement);
                }
            }
        }
    }

    private File[] getTestFiles(File projectDirectory) {
        File testDirectory = fileManager.getTestDirectory(projectDirectory);
        return testDirectory.listFiles();
    }

    private void writeFiles(File projectDirectory) {
        for (JavaClass javaClass : javaClasses) {
            Path path = Paths.get(projectDirectory.getPath() + "/src/" + javaClass.getName() + ".java");
            fileManager.writeFile(path, javaClass.toString());
        }
    }
}
