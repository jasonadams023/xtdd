package Requirement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequirementTest {
    @Test
    void should_HaveRequiredFields() {
        Requirement requirement = new Requirement("ClassName");

        assertEquals("ClassName", requirement.className);
    }
}
