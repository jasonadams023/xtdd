package projectStructure.functionObjects.functionRequirement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectStructure.functionObjects.signature.Signature;
import projectStructure.variable.Variable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EqualsTest {
    private FunctionRequirement functionRequirement1;

    @BeforeEach
    void setup() {
        List<String> inputTypes = new ArrayList<>();
        inputTypes.add("Integer");
        Signature signature = new Signature("ClassName", "Integer", inputTypes);

        List<Variable> inputs = new ArrayList<>();
        inputs.add(Variable.create(1));

        Variable returnValue = Variable.create(1);
        functionRequirement1 = FunctionRequirement.create(signature, inputs, returnValue);
    }

    @Test
    void should_ReturnFalseForNull() {
        boolean output = functionRequirement1.equals(null);

        assertFalse(output);
    }

    @Test
    void should_ReturnFalseForDifferentObject() {
        boolean output = functionRequirement1.equals(1);

        assertFalse(output);
    }

    @Test
    void should_ReturnFalseForDifferentSignature() {
        List<String> inputTypes = new ArrayList<>();
        inputTypes.add("Integer");
        Signature signature = new Signature("DifferentName", "Integer", inputTypes);

        List<Variable> inputs = new ArrayList<>();
        inputs.add(Variable.create(1));

        Variable returnValue = Variable.create(1);
        FunctionRequirement functionRequirement2 = FunctionRequirement.create(signature, inputs, returnValue);

        boolean output = functionRequirement1.equals(functionRequirement2);

        assertFalse(output);
    }

    @Test
    void should_ReturnFalseForDifferentInputs() {
        List<String> inputTypes = new ArrayList<>();
        inputTypes.add("Integer");
        Signature signature = new Signature("ClassName", "Integer", inputTypes);

        List<Variable> inputs = new ArrayList<>();
        inputs.add(Variable.create(2));

        Variable returnValue = Variable.create(1);
        FunctionRequirement functionRequirement2 = FunctionRequirement.create(signature, inputs, returnValue);

        boolean output = functionRequirement1.equals(functionRequirement2);

        assertFalse(output);
    }

    @Test
    void should_ReturnFalseForDifferentReturnValue() {
        List<String> inputTypes = new ArrayList<>();
        inputTypes.add("Integer");
        Signature signature = new Signature("ClassName", "Integer", inputTypes);

        List<Variable> inputs = new ArrayList<>();
        inputs.add(Variable.create(1));

        Variable returnValue = Variable.create(2);
        FunctionRequirement functionRequirement2 = FunctionRequirement.create(signature, inputs, returnValue);

        boolean output = functionRequirement1.equals(functionRequirement2);

        assertFalse(output);
    }

    @Test
    void should_ReturnTrueForSameValues() {
        List<String> inputTypes = new ArrayList<>();
        inputTypes.add("Integer");
        Signature signature = new Signature("ClassName", "Integer", inputTypes);

        List<Variable> inputs = new ArrayList<>();
        inputs.add(Variable.create(1));

        Variable returnValue = Variable.create(1);
        FunctionRequirement functionRequirement2 = FunctionRequirement.create(signature, inputs, returnValue);

        boolean output = functionRequirement1.equals(functionRequirement2);

        assertTrue(output);
    }
}
