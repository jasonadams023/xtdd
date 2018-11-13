package javaClass;

import Requirement.FunctionRequirement;
import fileManager.FileManager;
import function.Function;

import java.util.ArrayList;
import java.util.List;

public class JavaClass {
    private FileManager fileManager;
    private String name;
    List<Function> functions;

    public JavaClass(String name, FileManager fileManager) {
        this.name = name;
        this.fileManager = fileManager;
        this.functions = new ArrayList<>();
    }

    public void addRequirement(FunctionRequirement requirement) {
        if (requirement == null) {
            return;
        }

        boolean functionExists = false;

        for (Function function : functions) {
            if(function.matchesRequirement(requirement)) {
                functionExists = true;
            }
        }

        if (!functionExists) {
            functions.add(Function.createFromRequirement(requirement));
        }
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

    private String getHeader() {
        return "class " + name + " {\n";
    }

    private String getFooter() {
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
