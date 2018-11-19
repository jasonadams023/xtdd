package projectStructure.function;

import requirement.FunctionRequirement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MatchesRequirementTest {
    private Function function;

    @BeforeEach
    void setup() {
        Signature signature = new Signature("name", "void", null);
        FunctionRequirement functionRequirement = FunctionRequirement.create(signature, null, null);
        function = Function.createFromRequirement(functionRequirement);
    }

    @Test
    void should_ReturnFalse_WhenNameDoesNotMatch() {
        Signature signature = new Signature("wrong", "void", null);
        FunctionRequirement functionRequirement = FunctionRequirement.create(signature, null, null);

        boolean output = function.matchesRequirement(functionRequirement);

        assertFalse(output);
    }

    @Test
    void should_ReturnFalse_WhenReturnTypeDoesNotMatch() {
        Signature signature = new Signature("name", "String", null);
        FunctionRequirement functionRequirement = FunctionRequirement.create(signature, null, null);

        boolean output = function.matchesRequirement(functionRequirement);

        assertFalse(output);
    }

    @Test
    void should_ReturnTrue_WhenRequirementDoesMatchSignature() {
        Signature signature = new Signature("name", "void", null);
        FunctionRequirement functionRequirement = FunctionRequirement.create(signature, null, null);

        boolean output = function.matchesRequirement(functionRequirement);

        assertTrue(output);
    }
}
