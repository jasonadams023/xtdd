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
        FilesWrapper filesWrapper = new FilesWrapper();
        FileManager fileManager = new FileManager(filesWrapper);
        Generator generator = new Generator(exampleDirectory, fileManager);

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
        FilesWrapper filesWrapper = new FilesWrapper();
        FileManager fileManager = new FileManager(filesWrapper);
        Generator generator = new Generator(exampleDirectory, fileManager);

        generator.generate();

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
        FilesWrapper filesWrapper = new FilesWrapper();
        FileManager fileManager = new FileManager(filesWrapper);
        Generator generator = new Generator(exampleDirectory, fileManager);

        generator.generate();

        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(exampleSourceDirectory.getPath() + "/" + className + ".java")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(data.contains("class First {"));
        assertTrue(data.contains("static void example() {"));
        assertTrue(data.contains("static void different() {"));
    }
}
