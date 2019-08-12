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
        File directory = new File(exampleDirectory.getPath() + "/src");
        for(File file: directory.listFiles()) {
            if (!file.isDirectory()) {
                file.delete();
            }
        }
    }

    @Test
    void shouldGenerateClassBasedOnTestFiles() {
        generator.generate();

        String data1 = "";
        String data2 = "";

        try {
            data1 = new String(Files.readAllBytes(Paths.get(exampleDirectory.getPath() + "/src/First.java")));
            data2 = new String(Files.readAllBytes(Paths.get(exampleDirectory.getPath() + "/src/Second.java")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(data1.contains("class First {"));
        assertTrue(data2.contains("class Second {"));
    }

    @Test
    void shouldGenerateEmptyClass() {
        String className = "Empty";

        generator.generate();

        String data = "";
        data = readGeneratedClass(className, data);

        String expected = "class Empty {\n" +
                "}\n";

        assertEquals(expected, data);
    }



    @Test
    void shouldGenerateFunctionsBasedOnTestFile() {
        String className = "First";

        generator.generate();

        String data = "";
        data = readGeneratedClass(className, data);

        assertTrue(data.contains("class First {"));
        assertTrue(data.contains("static void example() {"));
        assertTrue(data.contains("static void different() {"));
    }

    @Test
    void should_GenerateFunctions_WithDifferentReturns() {
        String className = "Returns";

        generator.generate();

        String data = "";
        data = readGeneratedClass(className, data);

        assertTrue(data.contains("class Returns {"));
        assertTrue(data.contains("static String getNullString() {"));
        assertTrue(data.contains("return null"));
        assertTrue(data.contains("static int getNullInt() {"));
    }

    @Test
    void should_GenerateFunctions_WithNonNullReturns() {
        String className = "Returns";

        generator.generate();

        String data = "";
        data = readGeneratedClass(className, data);

        assertTrue(data.contains("class Returns {"));
        assertTrue(data.contains("static int getInt() {"));
        assertTrue(data.contains("return 7"));
    }

    @Test
    void should_GenerateFunctions_WithInput() {
        String className = "Inputs";

        generator.generate();

        String data = "";
        data = readGeneratedClass(className, data);

        assertTrue(data.contains("class Inputs {"));
        assertTrue(data.contains("static void setInt(int arg1) {"));
    }

    @Test
    void should_GenerateFunctions_WithMultipleInputs() {
        String className = "Inputs";

        generator.generate();

        String data = "";
        data = readGeneratedClass(className, data);

        assertTrue(data.contains("class Inputs {"));
        assertTrue(data.contains("static void setArgs(int arg1, String arg2) {"));
    }

    @Disabled
    @Test
    void should_GenerateFunctions_WithOutputsDependentOnInputs() {
        String className = "InputOutput";

        generator.generate();

        String data = "";
        data = readGeneratedClass(className, data);

        assertTrue(data.contains("class InputOutput {"));
        assertTrue(data.contains("static int add(int arg1, int arg2) {"));
        assertTrue(data.contains("return 10;"));
        assertTrue(data.contains("return 11;"));
    }

    private String readGeneratedClass(String className, String data) {
        try {
            data = new String(Files.readAllBytes(Paths.get(exampleSourceDirectory.getPath() + "/" + className + ".java")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
