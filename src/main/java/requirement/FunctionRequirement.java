package requirement;

import projectStructure.Variable.Variable;
import projectStructure.function.Signature;

import java.util.List;

public class FunctionRequirement {
    public Signature signature;
    public List<Variable> inputs;
    public Object returnValue;

    private FunctionRequirement(Signature signature, List<Variable> inputs, Object returnValue) {
        this.signature = signature;
        this.inputs = inputs;
        this.returnValue = returnValue;
    }

    public static FunctionRequirement create(Signature signature, List<Variable> inputs, Object returnValue) {
        return new FunctionRequirement(signature, inputs, returnValue);
    }
}
