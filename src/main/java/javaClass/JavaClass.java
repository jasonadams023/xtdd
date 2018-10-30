package javaClass;

import function.Function;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class JavaClass {
    FileReader fileReader;
    File file;
    String name;
    List<Function> functions;

    public JavaClass(File file) {
        this.file = file;
        this.name = generateName();
        this.functions = new ArrayList<>();
    }

    JavaClass(File file, FileReader files) {
        this.file = file;
        this.name = generateName();
        this.functions = new ArrayList<>();
        this.fileReader = files;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(getHeader());

        for (Function function : functions) {
            builder.append(function.toString());
        }

        builder.append(getFooter());

        return  builder.toString();
    }

    String generateName() {
        String fileName = file.getName();
        return fileName.substring(0, fileName.length() - 9);
    }

    void readFile() {
        functions = new ArrayList<>();
        List<String> lines = getLines();

        for (String line: lines) {
            addFunctionFromLine(line);
        }
    }

    Function generateFunction(String line) {
        Function output = null;
        if (line.contains(this.name + ".")) {
            String functionCall = line.split(Pattern.quote("."))[1];
            String functionName = functionCall.split(Pattern.quote("("))[0];

            output = new Function();
            output.setName(functionName);
        }
        return output;
    }

    void addFunctionFromLine(String line) {
        Function generated = generateFunction(line);

        if(generated != null) {
            functions.add(generateFunction(line));
        }
    }

    List<String> getLines() {
        try {
            return fileReader.readAllLines(Paths.get(file.getPath()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    String getHeader() {
        return "class " + name + " {\n";
    }

    String getFooter() {
        return "}\n";
    }

    public String getName() {
        return name;
    }
}
