package Requirement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FunctionRequirementTest {
    @Test
    void should_HaveRequiredFields() {
        FunctionRequirement functionRequirement = new FunctionRequirement("name", "void", 7);

        assertEquals("name", functionRequirement.name);
        assertEquals("void", functionRequirement.returnType);
        assertEquals(7, (int) functionRequirement.returnValue);
    }
}
