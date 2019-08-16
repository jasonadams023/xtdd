package projectStructure.classObjects.classRequirement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectStructure.functionObjects.functionRequirement.FunctionRequirement;
import projectStructure.functionObjects.signature.Signature;
import projectStructure.variable.Variable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EqualsTest {
    private ClassRequirement classRequirement1;
    private ClassRequirement classRequirement2;

    @BeforeEach
    void setup() {
        List<String> inputTypes1 = new ArrayList<>();
        inputTypes1.add("Integer");
        Signature signature1 = new Signature("ClassName", "Integer", inputTypes1);

        List<Variable> inputs1 = new ArrayList<>();
        inputs1.add(Variable.create(1));

        Object returnValue1 = 1;
        FunctionRequirement functionRequirement1 = FunctionRequirement.create(signature1, inputs1, returnValue1);
        classRequirement1 = new ClassRequirement("name", functionRequirement1);

        List<String> inputTypes2 = new ArrayList<>();
        inputTypes2.add("Integer");
        Signature signature2 = new Signature("ClassName", "Integer", inputTypes2);

        List<Variable> inputs2 = new ArrayList<>();
        inputs2.add(Variable.create(1));

        Object returnValue2 = 1;
        FunctionRequirement functionRequirement2 = FunctionRequirement.create(signature2, inputs2, returnValue2);
        classRequirement2 = new ClassRequirement("name", functionRequirement2);
    }

    @Test
    void should_ReturnFalseForNull() {
        boolean output = classRequirement1.equals(null);

        assertFalse(output);
    }

    @Test
    void should_ReturnFalseForDifferentClass() {
        boolean output = classRequirement1.equals(1);

        assertFalse(output);
    }

    @Test
    void should_ReturnFalseWhenNamesDoNotMatch() {
        classRequirement2.name = "Different";

        boolean output = classRequirement1.equals(classRequirement2);

        assertFalse(output);
    }

    @Test
    void should_ReturnFalseWhenFunctionRequirementsDoNotMatch() {
        List<String> inputTypes = new ArrayList<>();
        inputTypes.add("Integer");
        Signature signature = new Signature("DifferentName", "Integer", inputTypes);

        List<Variable> inputs = new ArrayList<>();
        inputs.add(Variable.create(1));

        Object returnValue = 1;
        FunctionRequirement functionRequirement = FunctionRequirement.create(signature, inputs, returnValue);

        classRequirement2.function = functionRequirement;

        boolean output = classRequirement1.equals(classRequirement2);

        assertFalse(output);
    }

    @Test
    void should_ReturnFalseWhenFunctionRequirementsListsDoNotMatch() {
        List<String> inputTypes = new ArrayList<>();
        inputTypes.add("Integer");
        Signature signature = new Signature("DifferentName", "Integer", inputTypes);

        List<Variable> inputs = new ArrayList<>();
        inputs.add(Variable.create(1));

        Object returnValue = 1;
        FunctionRequirement functionRequirement = FunctionRequirement.create(signature, inputs, returnValue);

        classRequirement2.addFunctionRequirement(functionRequirement);

        boolean output = classRequirement1.equals(classRequirement2);

        assertFalse(output);
    }

    @Test
    void should_ReturnTrueWhenFieldsMatch() {
        boolean output = classRequirement1.equals(classRequirement2);

        assertTrue(output);
    }
}
