package generator;

import fileManager.FileManager;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class ConstructorTest {
    @Test
    void should_ReturnNewGenerator() {
        File directory = mock(File.class);
        FileManager fileManager = mock(FileManager.class);
        Generator generator = new Generator(directory, fileManager);

        assertNotNull(generator);
        assertEquals(fileManager, generator.files);
        assertEquals(directory, generator.directory);
    }
}
