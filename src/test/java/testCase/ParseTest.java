package testCase;

import org.junit.jupiter.api.Test;
import requirement.FunctionRequirement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ParseTest {
    @Test
    void should_ReturnNull_WhenNoRequirementsForGivenClass() {
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

        assertEquals("voidFunction", output.signature.name);
        assertEquals("void", output.signature.returnType);
        assertEquals(null, output.returnValue);
    }

    @Test
    void should_ReturnRequirements_ForStringFunction() {
        String className = "StringClass";

        List<String> lines = new ArrayList<>();
        lines.add("String output = StringClass.stringFunction();");
        lines.add("assertEquals(\"a string\", output);");

        FunctionRequirement output = TestCase.parse(lines, className);

        assertEquals("stringFunction", output.signature.name);
        assertEquals("String", output.signature.returnType);
        assertEquals("\"a string\"", output.returnValue);
    }

    @Test
    void should_ReturnRequirements_ForIntFunction() {
        String className = "IntClass";

        List<String> lines = new ArrayList<>();
        lines.add("int output = IntClass.intFunction();");
        lines.add("assertEquals(7, output);");

        FunctionRequirement output = TestCase.parse(lines, className);

        assertEquals("intFunction", output.signature.name);
        assertEquals("int", output.signature.returnType);
        assertEquals(7, output.returnValue);
    }

    @Test
    void should_ReturnRequirements_ForSingleInput() {
        String className = "InputClass";

        List<String> lines = new ArrayList<>();
        lines.add("     int num = 7;");
        lines.add("int output = InputClass.inputFunction(num);");
        lines.add("assertEquals(7, output);");

        FunctionRequirement output = TestCase.parse(lines, className);

        assertEquals(1, output.inputs.size());
        assertEquals("int", output.inputs.get(0).type);
    }

    @Test
    void should_ReturnRequirements_ForInputs() {
        String className = "InputClass";

        List<String> lines = new ArrayList<>();
        lines.add("int num = 7;");
        lines.add("String line = \"a string\";");
        lines.add("int output = InputClass.inputFunction(num, line);");
        lines.add("assertEquals(7, output);");

        FunctionRequirement output = TestCase.parse(lines, className);

        assertEquals(2, output.inputs.size());
    }
}
