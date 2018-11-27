package projectStructure.functionObjects.signature;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToStringTest {
    @Test
    void should_ReturnVoidSignature() {
        Signature signature = new Signature("function", "void", null);

        String output = signature.toString();

        assertEquals("static void function()", output);
    }

    @Test
    void should_ReturnWithDifferentSignature() {
        Signature signature = new Signature("anotherFunction", "String", null);

        String output = signature.toString();

        assertEquals("static String anotherFunction()", output);
    }

    @Test
    void should_ReturnWithInputs() {
        List<String> inputTypes = new ArrayList<>();
        inputTypes.add("int");
        inputTypes.add("String");
        Signature signature = new Signature("function", "void", inputTypes);

        String output = signature.toString();

        assertEquals("static void function(int arg1, String arg2)", output);
    }
}
