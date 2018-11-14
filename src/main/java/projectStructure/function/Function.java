package projectStructure.function;

import Requirement.FunctionRequirement;

public class Function {
    private String name;
    private String returnType;
    private Object returnValue;

    private Function(FunctionRequirement requirement) {
        this.name = requirement.name;
        this.returnType = requirement.returnType;
        this.returnValue = requirement.returnValue;
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
        return "static " + returnType + " " + name + "() {\n";
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
