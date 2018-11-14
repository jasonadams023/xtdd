package projectStructure.javaClass;

import Requirement.FunctionRequirement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddRequirementTest {
    @Test
    void should_AddFunction_BasedOnRequirement() {
        FunctionRequirement functionRequirement = new FunctionRequirement("function", "void");
        JavaClass javaClass = new JavaClass("Example");

        javaClass.addRequirement(functionRequirement);

        assertEquals(1, javaClass.functions.size());
    }

    @Test
    void should_NotAddFunction_WhenItAlreadyExists() {
        FunctionRequirement functionRequirement = new FunctionRequirement("function", "void");
        JavaClass javaClass = new JavaClass("Example");

        javaClass.addRequirement(functionRequirement);
        javaClass.addRequirement(functionRequirement);

        assertEquals(1, javaClass.functions.size());
    }

    @Test
    void should_NotAddFunction_WhenRequirementIsNull() {
        JavaClass javaClass = new JavaClass("Example");

        javaClass.addRequirement(null);

        assertEquals(0, javaClass.functions.size());
    }
}
