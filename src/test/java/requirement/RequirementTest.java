package requirement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RequirementTest {
    @Test
    void should_HaveRequiredFields() {
        Requirement requirement = new Requirement("ClassName", null);

        assertEquals("ClassName", requirement.className);
        assertNull(requirement.function);
    }
}
