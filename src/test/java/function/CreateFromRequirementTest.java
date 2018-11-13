package function;

import Requirement.FunctionRequirement;
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
        FunctionRequirement requirement = new FunctionRequirement("name", "void");

        Function output = Function.createFromRequirement(requirement);

        assertNotNull(output);
    }
}
