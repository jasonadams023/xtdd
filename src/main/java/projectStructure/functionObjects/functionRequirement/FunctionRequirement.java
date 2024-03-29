package projectStructure.functionObjects.functionRequirement;

import projectStructure.functionObjects.inputOutput.InputOutput;
import projectStructure.variable.Variable;
import projectStructure.functionObjects.signature.Signature;

import java.util.List;

public class FunctionRequirement {
    public Signature signature;
    public InputOutput inputOutput;

    private FunctionRequirement(Signature signature, InputOutput inputOutput) {
        this.signature = signature;
        this.inputOutput = inputOutput;
    }

    public static FunctionRequirement create(Signature signature, List<Variable> inputs, Variable returnValue) {
        InputOutput io = new InputOutput(inputs, returnValue);

        return new FunctionRequirement(signature, io);
    }

    public boolean equals(Object o) {
        if (o instanceof FunctionRequirement) {
            FunctionRequirement compareTo = (FunctionRequirement) o;

            if (this.signature.equals(compareTo.signature) && this.inputOutput.equals(compareTo.inputOutput)) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }
}
