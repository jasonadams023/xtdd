import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GeneratorTest {
    private File example = new File("./example");

    @AfterAll
    @BeforeAll
    void cleanup() {
        File directory = new File(example.getPath() + "/src");
        for(File file: directory.listFiles())
            if (!file.isDirectory())
                file.delete();
    }

    @Test
    void shouldGenerateClassBasedOnTestFiles() {
        Generator generator = new Generator(example);

        generator.generate();

        String data1 = "";
        String data2 = "";

        try {
            data1 = new String(Files.readAllBytes(Paths.get(example.getPath() + "/src/First.java")));
            data2 = new String(Files.readAllBytes(Paths.get(example.getPath() + "/src/Second.java")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(data1.contains("class First {"));
        assertTrue(data2.contains("class Second {"));
    }
}
