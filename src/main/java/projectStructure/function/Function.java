package projectStructure.function;

import requirement.FunctionRequirement;

import java.util.ArrayList;
import java.util.List;

public class Function {
    private Signature signature;
    private List<FunctionRequirement> requirements;

    private Function(FunctionRequirement requirement) {
        this.requirements = new ArrayList<>();
        this.requirements.add(requirement);
        this.signature = requirement.signature;
    }

    public static Function createFromRequirement(FunctionRequirement requirement) {
        if (requirement == null) {
            return null;
        }

        return new Function(requirement);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        if (!signature.name.equals("")) {
            builder.append(getHeader());
            getReturnStatement(builder);
            builder.append(getFooter());
        }

        return builder.toString();
    }

    private String getHeader() {
        String output = "static " + signature.returnType + " " + signature.name + "(";

        output += getInputs();

        output += ") {\n";

        return output;
    }

    private String getInputs() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < signature.inputTypes.size(); i++) {
            if(i > 0) {
                builder.append(", ");
            }

            builder.append(signature.inputTypes.get(i));
            builder.append(" arg");
            builder.append(i + 1);
        }

        return builder.toString();
    }

    private void getReturnStatement(StringBuilder builder) {
        if(!signature.returnType.equals("void")) {
            builder.append("return " + requirements.get(0).returnValue + ";\n");
        }
    }

    private String getFooter() {
        return "}\n";
    }

    public boolean equals(Object o) {
        if (o instanceof Function) {
            Function compareTo = (Function) o;
            return signature.name.equals(compareTo.signature.name);
        }

        return false;
    }

    public boolean matchesRequirement(FunctionRequirement requirement) {
        return (requirement.signature.name.equals(signature.name) && requirement.signature.returnType.equals(signature.returnType));
    }
}
