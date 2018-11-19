package projectStructure.function;

import requirement.FunctionRequirement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class CreateFromRequirementTest {
    @Test
    void should_ReturnNull_WhenGivenNull() {
        Function output = Function.createFromRequirement(null);

        assertNull(output);
    }

    @Test
    void should_ReturnFunction_WhenGivenNonNull() {
        Signature signature = new Signature("name", "void", null);
        FunctionRequirement functionRequirement = FunctionRequirement.create(signature, null, null);

        Function output = Function.createFromRequirement(functionRequirement);

        assertNotNull(output);
    }
}
