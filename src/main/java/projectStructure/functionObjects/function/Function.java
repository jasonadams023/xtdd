package projectStructure.functionObjects.function;

import projectStructure.functionObjects.inputOutput.InputOutput;
import projectStructure.functionObjects.signature.Signature;
import projectStructure.functionObjects.functionRequirement.FunctionRequirement;

import java.util.ArrayList;
import java.util.List;

public class Function {
    private Signature signature;
    List<InputOutput> inputOutputs;

    private Function(FunctionRequirement requirement) {
        this.inputOutputs = new ArrayList<>();
        this.inputOutputs.add(requirement.inputOutput);
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
        output.addAll(getBody());
        output.add(getFooter());

        return output;
    }

    private String getHeader() {
        return signature.toString() + " {\n";
    }

    private List<String> getBody() {
        List<String> strings = new ArrayList<>();

        if(signature.hasReturn()) {

            if (inputOutputs.size() == 1) {
                strings.add("\treturn " + inputOutputs.get(0).getReturnValue() + ";\n");
            } else {
                for (int i = 0; i < inputOutputs.size(); i++) {
                    String openingString;
                    if (i != 0) {
                        openingString = "\t} else ";
                    } else {
                        openingString = "\t";
                    }

                    openingString += ifStatement(i);

                    strings.add(openingString);
                    strings.add("\t\treturn " + inputOutputs.get(i).getReturnValue() + ";\n");
                }
                String closingString = "\t}\n";
                strings.add(closingString);
            }
        } else {
            strings.add("");
        }

        return strings;
    }

    private String ifStatement(int i) {
        return "if (arg1 == " + inputOutputs.get(i).getInputs().get(0).toString() + ") {\n";
    }

    private String getFooter() {
        return "}\n";
    }

    public boolean matchesRequirement(FunctionRequirement requirement) {
        return (requirement.signature.equals(signature));
    }

    public void addInputOutput(InputOutput inputOutput) {
        inputOutputs.add(inputOutput);
    }
}
