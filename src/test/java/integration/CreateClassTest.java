package integration;

import generator.Generator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CreateClassTest {
    private File exampleDirectory = new File("./example");
    private File exampleSourceDirectory = new File(exampleDirectory.getPath() + "/src");

    @AfterAll
    @BeforeAll
    void cleanup() {
        File directory = exampleSourceDirectory;
        for(File file: directory.listFiles()) {
            if (!file.isDirectory()) {
                file.delete();
            }
        }
    }

    @Test
    void shouldGenerateEmptyClass() {
        String className = "Empty";
        Generator generator = new Generator(exampleDirectory);

        File testFile = new File(exampleDirectory.getPath() + "/test/" + className + "Test.java");

        generator.createClass(testFile);

        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(exampleSourceDirectory.getPath() + "/" + className + ".java")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String expected = "class Empty {\n" +
                "}\n";

        assertEquals(expected, data);
    }

    @Test
    void shouldGenerateFunctionsBasedOnTestFile() {
        String className = "First";
        Generator generator = new Generator(exampleDirectory);

        File testFile = new File(exampleDirectory.getPath() + "/test/" + className + "Test.java");

        generator.createClass(testFile);

        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(exampleSourceDirectory.getPath() + "/" + className + ".java")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(data.contains("static void example() {"));
        assertTrue(data.contains("static void different() {"));
    }
}
