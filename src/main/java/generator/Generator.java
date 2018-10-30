package generator;

import javaClass.FileReader;
import javaClass.JavaClass;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class Generator {
    private final File directory;

    public Generator(File targetDirectory) {
        directory = targetDirectory;
    }

    public void generate() {
        File testDirectory = new File(directory.getPath() + "/test");
        File[] testFiles = testDirectory.listFiles();

        for (File testFile : testFiles) {
            FileReader reader = (path) -> Files.readAllLines(path);
            JavaClass javaClass = new JavaClass(testFile, reader);
            javaClass.readFile();

            String className = javaClass.getName();
            Path path = Paths.get(directory.getPath() + "/src/" + className + ".java");

            writeFile(path, javaClass.toString());
        }
    }

    void createClass(File testFile) {
        String className = getClassName(testFile);
        Path path = Paths.get(directory.getPath() + "/src/" + className + ".java");

        StringBuilder builder = new StringBuilder();

        builder.append("class ").append(className).append(" {\n");

        for (String signature : getFunctionSignatures(testFile)) {
            builder.append(generateFunction(signature));
        }

        builder.append("}\n");

        writeFile(path, builder.toString());
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

    //Will be moved to Class class
    String getClassName(File testFile) {
        String testFileName = testFile.getName();
        return testFileName.substring(0, testFileName.length() - 9);
    }

    //Will be moved to Function class
    List<String> getFunctionSignatures(File testFile) {
        List<String> output = new ArrayList<>();
        String className = getClassName(testFile);

        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(testFile.getPath()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(String line : lines) {
            if (line.contains(className + ".")) {
                String functionCall = line.split(Pattern.quote("."))[1];
                String functionName = functionCall.split(Pattern.quote("("))[0];
                output.add("void:" + functionName + ":");
            }
        }

        return output;
    }

    //will be moved to Function class
    String generateFunction(String signature) {
        String[] parts = signature.split(Pattern.quote(":"));
        String returnType = parts[0];
        String name = parts[1];

        return  "static " + returnType + " " + name + "() {\n" +
                "}\n";
    }
}
