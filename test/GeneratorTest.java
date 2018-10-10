import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GeneratorTest {
    File example = new File("./example");

    @AfterAll
    @BeforeAll
    void cleanup() {
        File directory = new File(example.getPath() + "/src");
        for(File file: directory.listFiles())
            if (!file.isDirectory())
                file.delete();
    }

    @Test
    void shouldWriteToFolder() {
        Generator.generate(example);

        File directory = new File(example.getPath() + "/src");
        assertTrue(directory.list().length > 0);
    }

    @Test
    void shouldCreateJavaFile() {
        Generator.generate(example);

        File file = new File(example.getPath() + "/src/First.java");
        assertTrue(file.exists());
    }

    @Test
    void shouldCreateSecondJavaFile() {
        Generator.generate(example);

        File file = new File(example.getPath() + "/src/Second.java");
        assertTrue(file.exists());
    }

    @Test
    void shouldGenerateClass() {
        Generator.generate(example);

        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(example.getPath() + "/src/First.java")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(data.contains("class First {"));
    }

    @Test
    void shouldGenerateSecondClass() {
        Generator.generate(example);

        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(example.getPath() + "/src/Second.java")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(data.contains("class Second {"));
    }
}
