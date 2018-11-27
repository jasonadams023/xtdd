package projectStructure.signature;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasReturnTest {
    @Test
    void should_ReturnFalse_WhenVoid() {
        Signature signature = new Signature("function", "void", null);

        boolean output = signature.hasReturn();

        assertFalse(output);
    }

    @Test
    void should_ReturnTrue_WhenNotVoid() {
        Signature signature = new Signature("function", "String", null);

        boolean output = signature.hasReturn();

        assertTrue(output);
    }
}
