package projectStructure.function;

import requirement.FunctionRequirement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EqualsTest {
    @Test
    void should_ReturnTrue_WhenNamesMatch () {
        Signature signature = new Signature("someName", "void", null);
        FunctionRequirement functionRequirement = FunctionRequirement.create(signature, null, null);
        Function function1 = Function.createFromRequirement(functionRequirement);
        Function function2 = Function.createFromRequirement(functionRequirement);

        boolean output = function1.equals(function2);

        assertTrue(output);
    }

    @Test
    void should_ReturnFalse_WhenNamesDoNotMatch () {
        Signature signature = new Signature("someName", "void", null);
        FunctionRequirement functionRequirement1 = FunctionRequirement.create(signature, null, null);
        Function function1 = Function.createFromRequirement(functionRequirement1);

        Signature signature2 = new Signature("someOtherName", "void", null);
        FunctionRequirement functionRequirement2 = FunctionRequirement.create(signature2, null, null);
        Function function2 = Function.createFromRequirement(functionRequirement2);

        boolean output = function1.equals(function2);

        assertFalse(output);
    }

    @Test
    void should_ReturnFalse_WhenDifferentObjects () {
        Signature signature = new Signature("someName", "void", null);
        FunctionRequirement functionRequirement = FunctionRequirement.create(signature, null, null);
        Function function1 = Function.createFromRequirement(functionRequirement);

        boolean output = function1.equals("Not a projectStructure.function");

        assertFalse(output);
    }
}
