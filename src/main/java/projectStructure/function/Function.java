package projectStructure.function;

import projectStructure.Variable.Variable;
import requirement.FunctionRequirement;

import java.util.List;

public class Function {
    private String name;
    private String returnType;
    private Object returnValue;
    private List<Variable> inputs;

    private Function(FunctionRequirement requirement) {
        this.name = requirement.name;
        this.returnType = requirement.returnType;
        this.returnValue = requirement.returnValue;
        this.inputs = requirement.inputs;
    }

    public static Function createFromRequirement(FunctionRequirement requirement) {
        if (requirement == null) {
            return null;
        }

        return new Function(requirement);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        if (!name.equals("")) {
            builder.append(getHeader());
            getReturnStatement(builder);
            builder.append(getFooter());
        }

        return builder.toString();
    }

    private String getHeader() {
        String output = "static " + returnType + " " + name + "(";

        for (int i = 0; i < inputs.size(); i++) {
            if(i > 0) {
                output += ", ";
            }

            output += inputs.get(i).type;
            output += " arg" + (i + 1);
        }

        output += ") {\n";

        return output;
    }

    private void getReturnStatement(StringBuilder builder) {
        if(!returnType.equals("void")) {
            builder.append("return " + returnValue + ";\n");
        }
    }

    private String getFooter() {
        return "}\n";
    }

    public boolean equals(Object o) {
        if (o instanceof Function) {
            Function compareTo = (Function) o;
            return name.equals(compareTo.name);
        }

        return false;
    }

    public boolean matchesRequirement(FunctionRequirement requirement) {
        return (requirement.name.equals(name) && requirement.returnType.equals(returnType));
    }
}
