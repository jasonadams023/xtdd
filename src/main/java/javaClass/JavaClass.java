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
            functions.add(generated);
        }
    }

    Function generateFunction(String line) {
        Function output = null;
        String functionName = extractFunctionNameFromLine(line);

        if (!functionName.equals("")) {
            output = new Function(functionName);
        }

        return output;
    }

    private String extractFunctionNameFromLine(String line) {
        String name = "";
        if (line.contains(this.name + ".")) {
            String functionCall = line.split(Pattern.quote("."))[1];
            name = functionCall.split(Pattern.quote("("))[0];
        }

        return name;
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
