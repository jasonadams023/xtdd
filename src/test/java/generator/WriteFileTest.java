package generator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WriteFileTest {
    private File exampleSourceDirectory = new File("./example/src");

    @BeforeEach
    @AfterAll
    void cleanup() {
        File directory = exampleSourceDirectory;
        for(File file: directory.listFiles()) {
            if (!file.isDirectory()) {
                file.delete();
            }
        }
    }

    @Test
    void should_CreateFile_WhenProvidedString() {
        Path targetPath = Paths.get(exampleSourceDirectory + "/Example.java");

        Generator.writeFile(targetPath, "some text");

        String data = "";
        try {
            data = new String(Files.readAllBytes(targetPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals("some text", data);
    }
}
