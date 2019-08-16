package projectStructure.classObjects.classRequirement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateTest {
    @Test
    void should_ReturnNewClassRequirementWithNoFunctionRequirements() {
        ClassRequirement classRequirement = ClassRequirement.create("ClassName");

        assertEquals("ClassName", classRequirement.name);
        assertTrue(classRequirement.functionRequirements.isEmpty());
    }
}
