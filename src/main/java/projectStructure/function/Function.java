package projectStructure.function;

import projectStructure.signature.Signature;
import requirements.FunctionRequirement;

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
        String output = "";

        output += getHeader();
        output += getBody();
        output += getFooter();

        return output;
    }

    private String getHeader() {
        return signature.toString() + " {\n";
    }

    private String getBody() {
        if(signature.hasReturn()) {
            return "return " + requirements.get(0).returnValue + ";\n";
        }

        return "";
    }

    private String getFooter() {
        return "}\n";
    }

    public boolean matchesRequirement(FunctionRequirement requirement) {
        return (requirement.signature.equals(signature));
    }
}
