package parsing.testFile;

import projectStructure.functionObjects.signature.Signature;
import projectStructure.classObjects.classRequirement.ClassRequirement;
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
    private TestFileParser parser;
    private FileManager fileManager;

    @BeforeEach
    void setup() {
        path = mock(Path.class);
        fileManager = mock(FileManager.class);
        parser = new TestFileParser(fileManager);
    }

    @Test
    void should_ReturnEmptyList_WhenNoRequirementsToReturn() {
        List<ClassRequirement> output = parser.parseTestFile(path);

        assertEquals(new ArrayList<ClassRequirement>(), output);
    }

    @Test
    void should_NotReturnRequirements_ForNonGeneratedClasses() {
        List<String> lines = new ArrayList<>();
        lines.add("import example.Example;");
        willReturn(lines).given(fileManager).readAllLines(path);

        List<ClassRequirement> output = parser.parseTestFile(path);

        assertEquals(0, output.size());
    }

    @Test
    void should_ReturnRequirement_ForEmptyClass() {
        List<String> lines = new ArrayList<>();
        lines.add(TestFileParser.startFlag);
        lines.add("import example.Example;");
        lines.add(TestFileParser.endFlag);
        willReturn(lines).given(fileManager).readAllLines(path);

        List<ClassRequirement> output = parser.parseTestFile(path);

        assertEquals(1, output.size());
        assertEquals("Example", output.get(0).name);
        assertNull(output.get(0).function);
    }

    @Test
    void should_ReturnRequirements_ForFunctions() {
        List<String> lines = new ArrayList<>();
        lines.add(TestFileParser.startFlag);
        lines.add("import example.Example;");
        lines.add(TestFileParser.endFlag);
        lines.add("@Test");
        lines.add("void should_Return() {");
        lines.add("String output = Example.function();");
        lines.add("}");
        willReturn(lines).given(fileManager).readAllLines(path);

        Signature expectedSignature = new Signature("function", "String", new ArrayList<>());

        List<ClassRequirement> output = parser.parseTestFile(path);

        assertEquals(2, output.size());
        assertEquals("Example", output.get(1).name);
        assertEquals(expectedSignature, output.get(1).function.signature);
    }

    @Test
    void should_ReturnRequirements_ForFunctions_WithReturnValues() {
        List<String> lines = new ArrayList<>();
        lines.add(TestFileParser.startFlag);
        lines.add("import example.Example;");
        lines.add(TestFileParser.endFlag);
        lines.add("@Test");
        lines.add("void should_ReturnString() {");
        lines.add("String output = Example.function();");
        lines.add("assertEquals(\"a string\", output);");
        lines.add("}");
        willReturn(lines).given(fileManager).readAllLines(path);

        Signature expectedSignature = new Signature("function", "String", new ArrayList<>());

        List<ClassRequirement> output = parser.parseTestFile(path);

        assertEquals(2, output.size());
        assertEquals("Example", output.get(1).name);
        assertEquals(expectedSignature, output.get(1).function.signature);
        assertEquals("\"a string\"", output.get(1).function.inputOutput.getReturnValue());
    }

    @Test
    void should_ReturnRequirements_ForFunctions_WithDifferentReturnValues() {
        List<String> lines = new ArrayList<>();
        lines.add(TestFileParser.startFlag);
        lines.add("import example.Example;");
        lines.add(TestFileParser.endFlag);
        lines.add("@Test");
        lines.add("void should_ReturnInt() {");
        lines.add("int output = Example.function();");
        lines.add("assertEquals(7, output);");
        lines.add("}");
        willReturn(lines).given(fileManager).readAllLines(path);

        Signature expectedSignature = new Signature("function", "int", new ArrayList<>());

        List<ClassRequirement> output = parser.parseTestFile(path);

        assertEquals(2, output.size());
        assertEquals("Example", output.get(1).name);
        assertEquals(expectedSignature, output.get(1).function.signature);
        assertEquals(7, (int) output.get(1).function.inputOutput.getReturnValue());
    }
}
