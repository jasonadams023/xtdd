import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GeneratorTest {
    @AfterAll
    @BeforeAll
    void cleanup() {
        File directory = new File("./generated/");
        for(File file: directory.listFiles())
            if (!file.isDirectory())
                file.delete();
    }

    @Test
    void shouldWriteToFolder() {
        Generator.generate();

        File file = new File("./generated/file.java");
        assertTrue(file.exists());
    }
}
