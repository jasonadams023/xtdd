import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CreateClassTest {
    private File exampleDirectory = new File("./example");
    private File exampleSourceDirectory = new File(exampleDirectory.getPath() + "/src");

    @AfterAll
    @BeforeAll
    void cleanup() {
        File directory = exampleSourceDirectory;
        for(File file: directory.listFiles())
            if (!file.isDirectory())
                file.delete();
    }

    @Test
    void shouldWriteToFolder() {
        String className = "Example";
        Generator generator = new Generator(exampleDirectory);

        File testFile = new File(exampleDirectory.getPath() + "/test/" + className + "Test.java");

        generator.createClass(testFile);
        assertTrue(exampleSourceDirectory.list().length > 0);
    }

    @Test
    void shouldCreateJavaFile() {
        String className = "Example";
        Generator generator = new Generator(exampleDirectory);

        File testFile = new File(exampleDirectory.getPath() + "/test/" + className + "Test.java");

        generator.createClass(testFile);
        File file = new File(exampleSourceDirectory.getPath() + "/Example.java");
        assertTrue(file.exists());
    }

    @Test
    void shouldGenerateClass() {
        String className = "Example";
        Generator generator = new Generator(exampleDirectory);

        File testFile = new File(exampleDirectory.getPath() + "/test/" + className + "Test.java");

        generator.createClass(testFile);

        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(exampleSourceDirectory.getPath() + "/" + className + ".java")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(data.contains("class " + className + " {"));
    }

    @Test
    void shouldGenerateDifferentClass() {
        String className = "Different";
        Generator generator = new Generator(exampleDirectory);

        File testFile = new File(exampleDirectory.getPath() + "/test/" + className + "Test.java");

        generator.createClass(testFile);
        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(exampleSourceDirectory.getPath() + "/" + className + ".java")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(data.contains("class " + className + " {"));
    }
}
