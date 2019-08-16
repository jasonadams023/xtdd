package projectStructure.classObjects.classRequirement;

import org.junit.jupiter.api.Test;
import projectStructure.functionObjects.functionRequirement.FunctionRequirement;
import projectStructure.functionObjects.signature.Signature;
import projectStructure.variable.Variable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddFunctionRequirementTest {
    @Test
    void should_AddFunctionRequirement() {
        ClassRequirement classRequirement = ClassRequirement.create("ClassName");

        List<String> inputTypes = new ArrayList<>();
        inputTypes.add("Integer");
        Signature signature = new Signature("DifferentName", "Integer", inputTypes);

        List<Variable> inputs = new ArrayList<>();
        inputs.add(Variable.create(1));

        Object returnValue = 1;
        FunctionRequirement functionRequirement = FunctionRequirement.create(signature, inputs, returnValue);

        classRequirement.addFunctionRequirement(functionRequirement);

        assertEquals(1, classRequirement.functionRequirements.size());
    }
}
