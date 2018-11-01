package generator;

import fileManager.FileManager;
import javaClass.JavaClass;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

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
        assertEquals(fileManager, generator.fileManager);
        assertEquals(directory, generator.directory);
        assertEquals(new ArrayList<JavaClass>(), generator.javaClasses);
    }
}
