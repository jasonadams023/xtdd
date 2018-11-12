package Requirement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequirementTest {
    @Test
    void should_HaveRequiredFields() {
        Requirement requirement = new Requirement("ClassName", "void", "functionName");

        assertEquals("ClassName", requirement.className);
        assertEquals("void", requirement.returnType);
        assertEquals("functionName", requirement.functionName);
    }
}
