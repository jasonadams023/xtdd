package function;

import Requirement.FunctionRequirement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MatchesRequirementTest {
    @Test
    void should_ReturnFalse_WhenNameDoesNotMatch() {
        FunctionRequirement functionRequirement = new FunctionRequirement("wrong", "void");
        Function function = new Function("name", "void");

        boolean output = function.matchesRequirement(functionRequirement);

        assertFalse(output);
    }

    @Test
    void should_ReturnFalse_WhenReturnTypeDoesNotMatch() {
        FunctionRequirement functionRequirement = new FunctionRequirement("name", "String");
        Function function = new Function("name", "void");

        boolean output = function.matchesRequirement(functionRequirement);

        assertFalse(output);
    }

    @Test
    void should_ReturnTrue_WhenRequirementDoesMatchSignature() {
        FunctionRequirement functionRequirement = new FunctionRequirement("name", "void");
        Function function = new Function("name", "void");

        boolean output = function.matchesRequirement(functionRequirement);

        assertTrue(output);
    }
}
