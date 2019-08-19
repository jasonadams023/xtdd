package projectStructure.functionObjects.function;

import org.junit.jupiter.api.Test;
import projectStructure.functionObjects.functionRequirement.FunctionRequirement;
import projectStructure.functionObjects.inputOutput.InputOutput;
import projectStructure.functionObjects.signature.Signature;
import projectStructure.variable.Variable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddInputOutputTest {
    @Test
    void should_AddInputOutput() {
        List<String> inputTypes = new ArrayList<>();
        inputTypes.add("int");
        Signature signature = new Signature("function", "int", inputTypes);

        List<Variable> inputs = new ArrayList<>();
        Variable arg1 = Variable.create(1);
        inputs.add(arg1);

        Variable returnValue = Variable.create("output");

        FunctionRequirement requirement = FunctionRequirement.create(signature, inputs, returnValue);
        Function function = Function.createFromRequirement(requirement);

        List<Variable> newInputs = new ArrayList<>();
        Variable arg2 = Variable.create(1);
        inputs.add(arg2);

        Object newReturnValue = "new output";

        InputOutput newInputOutput = new InputOutput(newInputs, Variable.create(newReturnValue));

        function.addInputOutput(newInputOutput);

        assertEquals(2, function.inputOutputs.size());
    }
}
