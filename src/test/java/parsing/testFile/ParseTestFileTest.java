package parsing.testFile;

import projectStructure.functionObjects.functionRequirement.FunctionRequirement;
import projectStructure.functionObjects.signature.Signature;
import projectStructure.classObjects.classRequirement.ClassRequirement;
import fileManager.FileManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        assertTrue(output.get(0).functionRequirements.isEmpty());
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

        List<ClassRequirement> classRequirements = parser.parseTestFile(path);
        List<FunctionRequirement> functionRequirements = classRequirements.get(0).functionRequirements;

        assertEquals(1, classRequirements.size());
        assertEquals("Example", classRequirements.get(0).name);
        assertEquals(expectedSignature, functionRequirements.get(0).signature);
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

        List<ClassRequirement> classRequirements = parser.parseTestFile(path);
        List<FunctionRequirement> functionRequirements = classRequirements.get(0).functionRequirements;

        assertEquals(1, classRequirements.size());
        assertEquals("Example", classRequirements.get(0).name);
        assertEquals(expectedSignature, functionRequirements.get(0).signature);
        assertEquals("\"a string\"", functionRequirements.get(0).inputOutput.getReturnValue());
    }

    @Test
    void should_ReturnRequirements_ForFunctions_WithDifferentReturnValues() {
        List<String> lines = new ArrayList<>();
        lines.add(TestFileParser.startFlag);
        lines.add("import example.Example;");
        lines.add(TestFileParser.endFlag);
        lines.add("@Test");
        lines.add("void should_ReturnInt() {");
        lines.add("Integer output = Example.function();");
        lines.add("assertEquals(7, output);");
        lines.add("}");
        willReturn(lines).given(fileManager).readAllLines(path);

        Signature expectedSignature = new Signature("function", "Integer", new ArrayList<>());

        List<ClassRequirement> classRequirements = parser.parseTestFile(path);
        List<FunctionRequirement> functionRequirements = classRequirements.get(0).functionRequirements;

        assertEquals(1, classRequirements.size());
        assertEquals("Example", classRequirements.get(0).name);
        assertEquals(1, functionRequirements.size());
        assertEquals(expectedSignature, functionRequirements.get(0).signature);
        assertEquals(7, (int) functionRequirements.get(0).inputOutput.getReturnValue());
    }

    @Test
    void should_BeIdempotent() {
        List<String> lines = new ArrayList<>();
        lines.add(TestFileParser.startFlag);
        lines.add("import example.Example;");
        lines.add(TestFileParser.endFlag);
        lines.add("@Test");
        lines.add("void should_ReturnInt() {");
        lines.add("Integer output = Example.function();");
        lines.add("assertEquals(7, output);");
        lines.add("}");
        willReturn(lines).given(fileManager).readAllLines(path);

        Path path2 = mock(Path.class);
        List<String> lines2 = new ArrayList<>();
        lines2.add(TestFileParser.startFlag);
        lines2.add("import example.Other;");
        lines2.add(TestFileParser.endFlag);
        lines2.add("@Test");
        lines2.add("void should_ReturnInt() {");
        lines2.add("Integer output = Other.other();");
        lines2.add("assertEquals(7, output);");
        lines2.add("}");
        willReturn(lines2).given(fileManager).readAllLines(path2);

        List<ClassRequirement> output1 = new ArrayList<>(parser.parseTestFile(path));
        List<ClassRequirement> output2 = new ArrayList<>(parser.parseTestFile(path2));
        List<ClassRequirement> output3 = new ArrayList<>(parser.parseTestFile(path));

        assertEquals(output1, output3);
        assertNotEquals(output1, output2);
    }
}
