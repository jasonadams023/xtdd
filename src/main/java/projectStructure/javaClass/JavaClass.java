package projectStructure.javaClass;

import requirements.FunctionRequirement;
import projectStructure.function.Function;

import java.util.ArrayList;
import java.util.List;

public class JavaClass {
    private String name;
    List<Function> functions;

    public JavaClass(String name) {
        this.name = name;
        this.functions = new ArrayList<>();
    }

    public void addRequirement(FunctionRequirement requirement) {
        if (requirement == null) {
            return;
        }

        addFunction(requirement);
    }

    private void addFunction(FunctionRequirement requirement) {
        for (Function function : functions) {
            if(function.matchesRequirement(requirement)) {
                return;
            }
        }

        functions.add(Function.createFromRequirement(requirement));
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
