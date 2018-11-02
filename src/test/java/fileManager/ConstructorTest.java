package fileManager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class ConstructorTest {
    @Test
    void should_ReturnNewFileManager() {
        FilesWrapper filesWrapper = mock(FilesWrapper.class);
        FileManager fileManager = new FileManager(filesWrapper);

        assertNotNull(fileManager);
        assertEquals(filesWrapper, fileManager.files);
    }
}
