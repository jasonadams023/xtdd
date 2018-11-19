package requirement;

import org.junit.jupiter.api.Test;
import projectStructure.function.Signature;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FunctionRequirementTest {
    @Test
    void should_HaveRequiredFields() {
        List<String> inputTypes = new ArrayList<>();
        inputTypes.add("int");
        Signature signature = new Signature("name", "void", null);
        FunctionRequirement functionRequirement = FunctionRequirement.create(signature, null, 7);

        assertEquals("name", functionRequirement.signature.name);
        assertEquals("void", functionRequirement.signature.returnType);
        assertEquals(7, (int) functionRequirement.returnValue);
    }
}
