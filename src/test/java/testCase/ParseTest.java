package testCase;

import org.junit.jupiter.api.Test;
import projectStructure.signature.Signature;
import requirements.FunctionRequirement;

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

        Signature expectedSignature = new Signature("voidFunction", "void", new ArrayList<>());

        FunctionRequirement output = TestCase.parse(lines, className);

        assertEquals(expectedSignature, output.signature);
        assertEquals(null, output.returnValue);
    }

    @Test
    void should_ReturnRequirements_ForStringFunction() {
        String className = "StringClass";

        List<String> lines = new ArrayList<>();
        lines.add("String output = StringClass.stringFunction();");
        lines.add("assertEquals(\"a string\", output);");

        Signature expectedSignature = new Signature("stringFunction", "String", new ArrayList<>());

        FunctionRequirement output = TestCase.parse(lines, className);

        assertEquals(expectedSignature, output.signature);
        assertEquals("\"a string\"", output.returnValue);
    }

    @Test
    void should_ReturnRequirements_ForIntFunction() {
        String className = "IntClass";

        List<String> lines = new ArrayList<>();
        lines.add("int output = IntClass.intFunction();");
        lines.add("assertEquals(7, output);");

        Signature expectedSignature = new Signature("intFunction", "int", new ArrayList<>());

        FunctionRequirement output = TestCase.parse(lines, className);

        assertEquals(expectedSignature, output.signature);
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
