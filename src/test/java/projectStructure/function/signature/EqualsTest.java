package projectStructure.function.signature;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EqualsTest {
    @Test
    void should_ReturnFalseWhenDifferentObjects() {
        Signature signature = new Signature("functionName", "void", null);

        boolean output = signature.equals("string");

        assertFalse(output);
    }

    @Test
    void should_ReturnFalseWhenDifferentNames() {
        Signature signature = new Signature("functionName", "void", null);
        Signature signature2 = new Signature("differentName", "void", null);

        boolean output = signature.equals(signature2);

        assertFalse(output);
    }

    @Test
    void should_ReturnFalseWhenDifferentReturnTypes() {
        Signature signature = new Signature("functionName", "void", null);
        Signature signature2 = new Signature("functionName", "String", null);

        boolean output = signature.equals(signature2);

        assertFalse(output);
    }

    @Test
    void should_ReturnFalseWhenDifferentInputs() {
        List<String> inputTypes = new ArrayList<>();
        inputTypes.add("String");
        Signature signature = new Signature("functionName", "void", inputTypes);
        Signature signature2 = new Signature("functionName", "void", null);

        boolean output = signature.equals(signature2);

        assertFalse(output);
    }

    @Test
    void should_ReturnTrueWhenFieldsAreEqual() {
        Signature signature = new Signature("functionName", "void", null);
        Signature signature2 = new Signature("functionName", "void", null);

        boolean output = signature.equals(signature2);

        assertTrue(output);
    }
}
