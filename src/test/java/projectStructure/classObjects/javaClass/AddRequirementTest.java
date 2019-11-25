package projectStructure.classObjects.javaClass;

import testHelpers.prefabs.Inputs;
import testHelpers.prefabs.Requirements;
import testHelpers.prefabs.Signatures;
import testHelpers.prefabs.Variables;
import projectStructure.functionObjects.function.Function;
import projectStructure.functionObjects.functionRequirement.FunctionRequirement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddRequirementTest {

    @Test
    void should_AddFunction_BasedOnRequirement() {
        JavaClass javaClass = new JavaClass("Example");

        javaClass.addRequirement(Requirements.nullRequirement);

        assertEquals(1, javaClass.functions.size());
    }

    @Test
    void should_NotAddFunction_WhenItAlreadyExists() {
        JavaClass javaClass = new JavaClass("Example");

        javaClass.addRequirement(Requirements.nullRequirement);
        javaClass.addRequirement(Requirements.nullRequirement);

        assertEquals(1, javaClass.functions.size());
    }

    @Test
    void should_AddNewRequirement_ToFunction() {
        FunctionRequirement functionRequirement = FunctionRequirement.create(Signatures.intIntSignature, Inputs.intOneInputs, Variables.intOneVariable);
        FunctionRequirement functionRequirement2 = FunctionRequirement.create(Signatures.intIntSignature, Inputs.intTwoInputs, Variables.intTwoVariable);

        JavaClass javaClass = new JavaClass("Example");

        Function expectedFunction = Function.createFromRequirement(functionRequirement);
        expectedFunction.addInputOutput(functionRequirement2.inputOutput);

        javaClass.addRequirement(functionRequirement);
        javaClass.addRequirement(functionRequirement2);

        assertEquals(1, javaClass.functions.size());
        assertEquals(expectedFunction.toStrings(), javaClass.functions.get(0).toStrings());
    }
}
