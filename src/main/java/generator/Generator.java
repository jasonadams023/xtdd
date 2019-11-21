package generator;

import projectStructure.classObjects.classRequirement.ClassRequirement;
import parsing.testFile.TestFileParser;
import fileManager.FileManager;
import projectStructure.classObjects.javaClass.JavaClass;
import projectStructure.functionObjects.functionRequirement.FunctionRequirement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

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
        File testDirectory = fileManager.getTestDirectory(projectDirectory);

        for (File testFile : getTestFiles(testDirectory)) {
            classRequirements.addAll(testFileParser.parseTestFile(testFile.toPath()));
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

    private List<File> getTestFiles(File directory) {
        List<File> testFiles = new ArrayList<>();
        List<File> files = Arrays.asList(directory.listFiles());

        for (File file : files) {
            if (file.isDirectory()) {
                testFiles.addAll(getTestFiles(file));
            } else if (file.getName().contains("Test.java")) {
                testFiles.add(file);
            }
        }

        return testFiles;
    }

    private void writeFiles(File projectDirectory) {
        for (JavaClass javaClass : javaClasses) {
            ensureMainDirectoryExists(projectDirectory);
            Path path = Paths.get(projectDirectory.getPath() + "/src/main/" + javaClass.getName() + ".java");
            fileManager.writeFile(path, javaClass.toString());
        }
    }

    private void ensureMainDirectoryExists(File projectDirectory) {
        File mainDirectory = new File(projectDirectory.getPath() + "/src/main/");
        if (!mainDirectory.exists()) {
            try {
                Files.createDirectory(mainDirectory.toPath());
            } catch (IOException error) {
                LOGGER.warning("Failed to write main directory\n" +
                        "Error message: " + error + "\n");
            }
        }
    }
}
