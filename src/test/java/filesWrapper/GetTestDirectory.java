package filesWrapper;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;

class GetTestDirectory {
    @Test
    void should_ReturnNullIfNoTestDirectoryExists() {
        FilesInterface filesInterface = mock(FilesInterface.class);
        FilesWrapper filesWrapper = new FilesWrapper(filesInterface);
        File directory = mock(File.class);

        File output = filesWrapper.getTestDirectory(directory);

        assertNull(output);
    }

    @Test
    void should_ReturnTestDirectoryIfItExists() {
        FilesInterface filesInterface = mock(FilesInterface.class);
        FilesWrapper filesWrapper = new FilesWrapper(filesInterface);
        File directory = mock(File.class);

        File testDirectory = mock(File.class);
        willReturn("test").given(testDirectory).getName();
        willReturn(true).given(testDirectory).isDirectory();

        File[] directories = new File[1];
        directories[0] = testDirectory;
        willReturn(directories).given(directory).listFiles();

        File output = filesWrapper.getTestDirectory(directory);

        assertEquals(testDirectory, output);
    }
}
