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

    public void addInputOutput(InputOutput inputOutput) {
        inputOutputs.add(inputOutput);
    }

    public boolean matchesRequirement(FunctionRequirement requirement) {
        return (requirement.signature.equals(signature));
    }

    public List<String> toStrings() {
        List<String> output = new ArrayList<>();

        output.add(getHeader());
        output.addAll(getBody());
        output.add(getFooter());

        return output;
    }

    private String getHeader() {
        return signature.toString() + " {\n";
    }

    private List<String> getBody() {
        List<String> body = new ArrayList<>();

        if(signature.hasReturn()) {
            if (inputOutputs.size() == 1) {
                body.add(returnStatement(0));
            } else {
                body.addAll(ifElse());
            }
        }

        body = indentLines(body);

        return body;
    }

    private List<String> ifElse() {
        List<String> ifElse = new ArrayList<>();

        for (int i = 0; i < inputOutputs.size(); i++) {
            ifElse.add(openingLine(i));
            ifElse.add("\t" + returnStatement(i));
        }

        ifElse.addAll(finalElse());

        return ifElse;
    }

    private String openingLine(int i) {
        String openingLine = "";

        if (i != 0) {
            openingLine = "} else ";
        }

        openingLine += ifStatement(i);
        return openingLine;
    }

    private List<String> indentLines(List<String> inputStrings) {
        List<String> outputStrings = new ArrayList<>();

        for (String string : inputStrings) {
            outputStrings.add("\t" + string);
        }

        return outputStrings;
    }

    private String returnStatement(int i) {
        return "return " + inputOutputs.get(i).getReturnValue() + ";\n";
    }

    private String ifStatement(int i) {
        return "if (arg1 == " + inputOutputs.get(i).getInputs().get(0).toString() + ") {\n";
    }

    private List<String> finalElse() {
        List<String> finalElse = new ArrayList<>();

        finalElse.add("} else {\n");
        finalElse.add("\t" + returnStatement(0));
        finalElse.add("}\n");

        return finalElse;
    }

    private String getFooter() {
        return "}\n";
    }
}
