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

        for (int i = 0; i < functions.size(); i++) {
            Function function = functions.get(i);

            for (String line : function.toStrings()) {
                if (!line.isEmpty()) {
                    builder.append("\t");
                }
                builder.append(line);
            }

            if (i < functions.size() - 1) {
                builder.append("\n");
            }
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
