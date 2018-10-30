package filesWrapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class Constructor {
    @Test
    void should_ReturnNewFilesWrapper() {
        FilesInterface files = mock(FilesInterface.class);
        FilesWrapper filesWrapper = new FilesWrapper(files);

        assertNotNull(filesWrapper);
        assertNotNull(filesWrapper.files);
    }
}
