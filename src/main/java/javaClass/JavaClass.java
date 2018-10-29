package javaClass;

import function.Function;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
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

        builder.append("class ");
        builder.append(name);
        builder.append(" {\n");

        for (Function function : functions) {
            builder.append(function.toString());
        }

        builder.append("}\n");

        return  builder.toString();
    }

    String generateName() {
        String fileName = file.getName();
        return fileName.substring(0, fileName.length() - 9);
    }

    void readFile() {
        List<String> lines;
        functions = new ArrayList<>();
        try {
            lines = fileReader.readAllLines(Paths.get(file.getPath()));
        } catch (Exception e) {
            lines = Collections.emptyList();
            e.printStackTrace();
        }

        for (String line: lines) {
            Function generated = generateFunction(line);
            if(generated != null) {
                functions.add(generateFunction(line));
            }
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
}
