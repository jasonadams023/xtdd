package projectStructure.classObjects.classRequirement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CreateTest {
    @Test
    void should_ReturnNewClassRequirementWithNoFunctionRequirements() {
        ClassRequirement classRequirement = ClassRequirement.create("ClassName");

        assertEquals("ClassName", classRequirement.name);
        assertNull(classRequirement.function);
    }
}
