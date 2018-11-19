package testParser;

import requirement.Requirement;
import fileManager.FileManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;

class ParseTestFileTest {
    private Path path;
    private TestParser parser;
    private FileManager fileManager;

    @BeforeEach
    void setup() {
        path = mock(Path.class);
        fileManager = mock(FileManager.class);
        parser = new TestParser(fileManager);
    }

    @Test
    void should_ReturnEmptyList_WhenNoRequirementsToReturn() {
        List<Requirement> output = parser.parseTestFile(path);

        assertEquals(new ArrayList<Requirement>(), output);
    }

    @Test
    void should_NotReturnRequirements_ForNonGeneratedClasses() {
        List<String> lines = new ArrayList<>();
        lines.add("import example.Example;");
        willReturn(lines).given(fileManager).readAllLines(path);

        List<Requirement> output = parser.parseTestFile(path);

        assertEquals(0, output.size());
    }

    @Test
    void should_ReturnRequirement_ForEmptyClass() {
        List<String> lines = new ArrayList<>();
        lines.add(TestParser.startFlag);
        lines.add("import example.Example;");
        lines.add(TestParser.endFlag);
        willReturn(lines).given(fileManager).readAllLines(path);

        List<Requirement> output = parser.parseTestFile(path);

        assertEquals(1, output.size());
        assertEquals("Example", output.get(0).className);
        assertNull(output.get(0).function);
    }

    @Test
    void should_ReturnRequirements_ForFunctions() {
        List<String> lines = new ArrayList<>();
        lines.add(TestParser.startFlag);
        lines.add("import example.Example;");
        lines.add(TestParser.endFlag);
        lines.add("@Test");
        lines.add("void should_Return() {");
        lines.add("String output = Example.function();");
        lines.add("}");
        willReturn(lines).given(fileManager).readAllLines(path);

        List<Requirement> output = parser.parseTestFile(path);

        assertEquals(2, output.size());
        assertEquals("Example", output.get(1).className);
        assertEquals("String", output.get(1).function.signature.returnType);
        assertEquals("function", output.get(1).function.signature.name);
    }

    @Test
    void should_ReturnRequirements_ForFunctions_WithReturnValues() {
        List<String> lines = new ArrayList<>();
        lines.add(TestParser.startFlag);
        lines.add("import example.Example;");
        lines.add(TestParser.endFlag);
        lines.add("@Test");
        lines.add("void should_ReturnString() {");
        lines.add("String output = Example.function();");
        lines.add("assertEquals(\"a string\", output);");
        lines.add("}");
        willReturn(lines).given(fileManager).readAllLines(path);

        List<Requirement> output = parser.parseTestFile(path);

        assertEquals(2, output.size());
        assertEquals("Example", output.get(1).className);
        assertEquals("String", output.get(1).function.signature.returnType);
        assertEquals("function", output.get(1).function.signature.name);
        assertEquals("\"a string\"", output.get(1).function.returnValue);
    }

    @Test
    void should_ReturnRequirements_ForFunctions_WithDifferentReturnValues() {
        List<String> lines = new ArrayList<>();
        lines.add(TestParser.startFlag);
        lines.add("import example.Example;");
        lines.add(TestParser.endFlag);
        lines.add("@Test");
        lines.add("void should_ReturnInt() {");
        lines.add("int output = Example.function();");
        lines.add("assertEquals(7, output);");
        lines.add("}");
        willReturn(lines).given(fileManager).readAllLines(path);

        List<Requirement> output = parser.parseTestFile(path);

        assertEquals(2, output.size());
        assertEquals("Example", output.get(1).className);
        assertEquals("int", output.get(1).function.signature.returnType);
        assertEquals("function", output.get(1).function.signature.name);
        assertEquals(7, (int) output.get(1).function.returnValue);
    }
}
