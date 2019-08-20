package projectStructure.classObjects.javaClass;

import projectStructure.functionObjects.functionRequirement.FunctionRequirement;
import projectStructure.functionObjects.function.Function;

import java.util.ArrayList;
import java.util.List;

public class JavaClass {
    private String name;
    List<Function> functions;

    public JavaClass(String name) {
        this.name = name;
        this.functions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addRequirement(FunctionRequirement requirement) {
        addFunction(requirement);
    }

    public boolean equals(Object o) {
        if (o instanceof JavaClass) {
            JavaClass compareTo = (JavaClass) o;
            return name.equals(compareTo.name);
        }

        return false;
    }

    private void addFunction(FunctionRequirement requirement) {
        for (Function function : functions) {
            if(function.matchesRequirement(requirement)) {
                function.addInputOutput(requirement.inputOutput);
                return;
            }
        }

        functions.add(Function.createFromRequirement(requirement));
    }

    public String toString() {
        String output = "";

        output += getHeader();
        output += getBody();
        output += getFooter();

        return output;
    }

    private String getHeader() {
        return "class " + name + " {\n";
    }

    private String getBody() {
        String output = "";

        output += getFunctions();

        return output;
    }

    private String getFooter() {
        return "}\n";
    }

    private StringBuilder getFunctions() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < functions.size(); i++) {
            Function function = functions.get(i);

            builder.append(formatFunction(function));

            if (i < functions.size() - 1) {
                builder.append("\n");
            }
        }

        return builder;
    }

    private StringBuilder formatFunction(Function function) {
        StringBuilder builder = new StringBuilder();

        for (String line : function.toStrings()) {
            if (!line.isEmpty()) {
                builder.append("\t");
            }
            builder.append(line);
        }

        return builder;
    }
}
