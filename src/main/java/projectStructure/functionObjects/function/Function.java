package projectStructure.functionObjects.function;

import projectStructure.functionObjects.signature.Signature;
import projectStructure.functionObjects.functionRequirement.FunctionRequirement;

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

    public List<String> toStrings() {
        List<String> output = new ArrayList<>();
        output.add("");

        output.add(getHeader());
        output.add(getBody());
        output.add(getFooter());

        return output;
    }

    private String getHeader() {
        return signature.toString() + " {\n";
    }

    private String getBody() {
        if(signature.hasReturn()) {
            return "\treturn " + requirements.get(0).inputOutput.getReturnValue() + ";\n";
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
