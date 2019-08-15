package component;

import fileManager.FileManager;
import fileManager.FilesWrapper;
import generator.Generator;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GeneratorTest {
    private File exampleDirectory = new File("./example");
    private File exampleSourceDirectory = new File(exampleDirectory.getPath() + "/src");
    private Generator generator;

    @BeforeAll
    void validateDirectory() throws IOException {
        if (!exampleSourceDirectory.exists()) {
            Files.createDirectory(exampleSourceDirectory.toPath());
        }
    }

    @BeforeEach
    void setup() {
        FilesWrapper filesWrapper = new FilesWrapper();
        FileManager fileManager = new FileManager(filesWrapper);
        generator = new Generator(exampleDirectory, fileManager);
    }

    @BeforeEach
    @AfterAll
    void cleanup() {
        for(File file: exampleSourceDirectory.listFiles()) {
            if (!file.isDirectory()) {
                file.delete();
            }
        }
    }

    @Test
    void shouldGenerateClassBasedOnTestFiles() {
        generator.generate();
        String data1 = readGeneratedClass("First");
        String data2 = readGeneratedClass("Second");

        assertTrue(data1.contains("class First {"));
        assertTrue(data2.contains("class Second {"));
    }

    @Test
    void shouldGenerateEmptyClass() {
        String className = "Empty";

        generator.generate();
        String data = readGeneratedClass(className);

        String expected = "class Empty {\n" +
                "}\n";

        assertEquals(expected, data);
    }

    @Test
    void shouldGenerateFunctionsBasedOnTestFile() {
        String className = "First";

        generator.generate();
        String data = readGeneratedClass(className);

        assertTrue(data.contains("class " + className + " {"));
        assertTrue(data.contains("static void example() {"));
        assertTrue(data.contains("static void different() {"));
    }

    @Test
    void should_GenerateFunctions_WithDifferentReturns() {
        String className = "Returns";

        generator.generate();
        String data = readGeneratedClass(className);

        assertTrue(data.contains("class " + className + " {"));
        assertTrue(data.contains("static String getNullString() {"));
        assertTrue(data.contains("return null"));
        assertTrue(data.contains("static Integer getNullInt() {"));
    }

    @Test
    void should_GenerateFunctions_WithNonNullInts() {
        String className = "Returns";

        generator.generate();
        String data = readGeneratedClass(className);

        assertTrue(data.contains("class " + className + " {"));
        assertTrue(data.contains("static Integer getInt() {"));
        assertTrue(data.contains("return 7"));
    }

    @Test
    void should_GenerateFunctions_WithNonNullStrings() {
        String className = "Returns";

        generator.generate();
        String data = readGeneratedClass(className);

        assertTrue(data.contains("class " + className + " {"));
        assertTrue(data.contains("static String getString() {"));
        assertTrue(data.contains("return \"hello world\";"));
    }

    @Test
    void should_GenerateFunctions_WithInput() {
        String className = "Inputs";

        generator.generate();
        String data = readGeneratedClass(className);

        assertTrue(data.contains("class " + className + " {"));
        assertTrue(data.contains("static void setInt(Integer arg1) {"));
    }

    @Test
    void should_GenerateFunctions_WithMultipleInputs() {
        String className = "Inputs";

        generator.generate();
        String data = readGeneratedClass(className);

        assertTrue(data.contains("class " + className + " {"));
        assertTrue(data.contains("static void setArgs(Integer arg1, String arg2) {"));
    }

    @Disabled
    @Test
    void should_GenerateFunctions_WithIfElseStatements() {
        String className = "IfElse";

        generator.generate();
        String data = readGeneratedClass(className);

        assertTrue(data.contains("class " + className + " {"));
        assertTrue(data.contains("static String add(int arg1) {"));
        assertTrue(data.contains("if (arg1 =="));
        assertTrue(data.contains("return \"hello\";"));
        assertTrue(data.contains("else {"));
        assertTrue(data.contains("return \"bye\";"));
    }

    private String readGeneratedClass(String className) {
        String data = "";

        try {
            data = new String(Files.readAllBytes(Paths.get(exampleSourceDirectory.getPath() + "/" + className + ".java")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
