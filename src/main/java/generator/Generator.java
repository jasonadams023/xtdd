package generator;

import fileManager.FileManager;
import javaClass.JavaClass;
import javaClass.JavaClassFactory;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Generator {
    List<JavaClass> javaClasses;
    FileManager fileManager;
    File directory;
    JavaClassFactory javaClassFactory;

    static final String startFlag = "//beginning of classes to generate";
    static final String endFlag = "//end of classes to generate";

    public Generator(File directory, FileManager fileManager, JavaClassFactory javaClassFactory) {
        this.directory = directory;
        this.fileManager = fileManager;
        this.javaClassFactory = javaClassFactory;
        this.javaClasses = new ArrayList<>();
    }

    public void generate() {
        File testDirectory = fileManager.getTestDirectory(directory);
        File[] testFiles = testDirectory.listFiles();

        for (File testFile : testFiles) {
            JavaClass javaClass = new JavaClass(testFile, fileManager);
            javaClass.readFile();

            String className = javaClass.getName();
            Path path = Paths.get(directory.getPath() + "/src/" + className + ".java");

            fileManager.writeFile(path, javaClass.toString());
        }
    }

    void createClasses(Path path) {
        List<String> lines = fileManager.readAllLines(path);
        boolean flag = false;

        for (String line : lines) {
            if(line.equals(startFlag)) {
                flag = true;
                continue;
            }

            if (line.equals(endFlag)) {
                flag = false;
                continue;
            }

            if(flag) {
                String className = getClassNameFromImport(line);
                addClass(className);
            }
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
            javaClasses.add(javaClassFactory.newJavaClass(className));
        }
    }

    void populateClasses(Path path) {
        for (JavaClass javaClass : javaClasses) {
            javaClass.createFunctionsFromPath(path);
        }
    }

    void readFiles() {
        File testDirectory = fileManager.getTestDirectory(directory);
        File[] testFiles = testDirectory.listFiles();

        for (File testFile : testFiles) {
            createClasses(testFile.toPath());
        }

        for (File testFile : testFiles) {
            populateClasses(testFile.toPath());
        }
    }

    void writeFiles() {
        for (JavaClass javaClass : javaClasses) {
            Path path = Paths.get(directory.getPath() + "/src/" + javaClass.getName() + ".java");
            fileManager.writeFile(path, javaClass.toString());
        }
    }
}
