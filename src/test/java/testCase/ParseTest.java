package testCase;

import org.junit.jupiter.api.Test;
import requirement.FunctionRequirement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ParseTest {
    @Test
    void should_ReturnVoid_WhenNoRequirementsForGivenClass() {
        String className = "VoidClass";

        List<String> lines = new ArrayList<>();
        lines.add("OtherClass.function();");

        FunctionRequirement output = TestCase.parse(lines, className);

        assertNull(output);
    }

    @Test
    void should_ReturnRequirements_ForVoidFunction() {
        String className = "VoidClass";

        List<String> lines = new ArrayList<>();
        lines.add("VoidClass.voidFunction();");

        FunctionRequirement output = TestCase.parse(lines, className);

        assertEquals("voidFunction", output.name);
        assertEquals("void", output.returnType);
        assertEquals(null, output.returnValue);
    }

    @Test
    void should_ReturnRequirements_ForStringFunction() {
        String className = "StringClass";

        List<String> lines = new ArrayList<>();
        lines.add("String output = StringClass.stringFunction();");
        lines.add("assertEquals(\"a string\", output);");

        FunctionRequirement output = TestCase.parse(lines, className);

        assertEquals("stringFunction", output.name);
        assertEquals("String", output.returnType);
        assertEquals("\"a string\"", output.returnValue);
    }

    @Test
    void should_ReturnRequirements_ForIntFunction() {
        String className = "IntClass";

        List<String> lines = new ArrayList<>();
        lines.add("int output = IntClass.intFunction();");
        lines.add("assertEquals(7, output);");

        FunctionRequirement output = TestCase.parse(lines, className);

        assertEquals("intFunction", output.name);
        assertEquals("int", output.returnType);
        assertEquals(7, output.returnValue);
    }
}
