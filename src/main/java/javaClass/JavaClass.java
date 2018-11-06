package javaClass;

import fileManager.FileManager;
import function.Function;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class JavaClass {
    FileManager fileManager;
    String name;
    List<Function> functions;

    public JavaClass(String name, FileManager fileManager) {
        this.name = name;
        this.fileManager = fileManager;
        this.functions = new ArrayList<>();
    }

    public void createFunctionsFromPath(Path path) {
        List<String> lines = fileManager.readAllLines(path);

        for (String line: lines) {
            addFunctionFromLine(line);
        }
    }

    void addFunctionFromLine(String line) {
        Function generated = generateFunction(line);

        if(generated != null) {
            functions.add(generateFunction(line));
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

    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(getHeader());

        for (Function function : functions) {
            builder.append(function.toString());
        }

        builder.append(getFooter());

        return  builder.toString();
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

    public boolean equals(Object o) {
        if (o instanceof JavaClass) {
            JavaClass compareTo = (JavaClass) o;
            return name.equals(compareTo.name);
        }

        return false;
    }
}
