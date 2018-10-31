package generator;

import filesWrapper.FilesWrapper;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class ConstructorTest {
    @Test
    void should_ReturnNewGenerator() {
        File directory = mock(File.class);
        FilesWrapper filesWrapper = mock(FilesWrapper.class);
        Generator generator = new Generator(directory, filesWrapper);

        assertNotNull(generator);
        assertEquals(filesWrapper, generator.files);
        assertEquals(directory, generator.directory);
    }
}
