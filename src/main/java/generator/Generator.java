package generator;

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
    FileManager fileManager;
    File directory;

    private final String startFlag = "//beginning of classes to generate";
    private final String endFlag = "//end of classes to generate";

    public Generator(File directory, FileManager fileManager) {
        this.directory = directory;
        this.fileManager = fileManager;
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

    void createClasses() {
        Path path = Paths.get("badPath");
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
                String[] split = line.split(Pattern.quote("."));
                String last = split[split.length - 1];
                String className = last.split(Pattern.quote(";"))[0];

                javaClasses.add(new JavaClass(className));
            }
        }
    }
}
