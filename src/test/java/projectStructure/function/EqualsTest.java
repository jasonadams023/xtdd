package projectStructure.function;

import Requirement.FunctionRequirement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EqualsTest {
    @Test
    void should_ReturnTrue_WhenNamesMatch () {
        FunctionRequirement functionRequirement = new FunctionRequirement("someName", "void");
        Function function1 = Function.createFromRequirement(functionRequirement);
        Function function2 = Function.createFromRequirement(functionRequirement);

        boolean output = function1.equals(function2);

        assertTrue(output);
    }

    @Test
    void should_ReturnFalse_WhenNamesDoNotMatch () {
        FunctionRequirement functionRequirement1 = new FunctionRequirement("someName", "void");
        Function function1 = Function.createFromRequirement(functionRequirement1);
        FunctionRequirement functionRequirement2 = new FunctionRequirement("someOtherName", "void");
        Function function2 = Function.createFromRequirement(functionRequirement2);

        boolean output = function1.equals(function2);

        assertFalse(output);
    }

    @Test
    void should_ReturnFalse_WhenDifferentObjects () {
        FunctionRequirement functionRequirement1 = new FunctionRequirement("someName", "void");
        Function function1 = Function.createFromRequirement(functionRequirement1);

        boolean output = function1.equals("Not a projectStructure.function");

        assertFalse(output);
    }
}
