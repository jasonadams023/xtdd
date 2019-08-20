package projectStructure.classObjects.javaClass;

import projectStructure.functionObjects.signature.Signature;
import projectStructure.functionObjects.functionRequirement.FunctionRequirement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddRequirementTest {
    @Test
    void should_AddFunction_BasedOnRequirement() {
        Signature signature = new Signature("function", "void", null);
        FunctionRequirement functionRequirement = FunctionRequirement.create(signature, null, null);
        JavaClass javaClass = new JavaClass("Example");

        javaClass.addRequirement(functionRequirement);

        assertEquals(1, javaClass.functions.size());
    }

    @Test
    void should_NotAddFunction_WhenItAlreadyExists() {
        Signature signature = new Signature("function", "void", null);
        FunctionRequirement functionRequirement = FunctionRequirement.create(signature, null, null);
        JavaClass javaClass = new JavaClass("Example");

        javaClass.addRequirement(functionRequirement);
        javaClass.addRequirement(functionRequirement);

        assertEquals(1, javaClass.functions.size());
    }
}
