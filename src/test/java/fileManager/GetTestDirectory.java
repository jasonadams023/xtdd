package fileManager;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;

class GetTestDirectory {
    @Test
    void should_ReturnNullIfNoTestDirectoryExists() {
        FilesWrapper filesWrapper = mock(FilesWrapper.class);
        FileManager fileManager = new FileManager(filesWrapper);
        File directory = mock(File.class);

        File output = fileManager.getTestDirectory(directory);

        assertNull(output);
    }

    @Test
    void should_ReturnTestDirectoryIfItExists() {
        FilesWrapper filesWrapper = mock(FilesWrapper.class);
        FileManager fileManager = new FileManager(filesWrapper);
        File directory = mock(File.class);

        File testDirectory = mock(File.class);
        willReturn("test").given(testDirectory).getName();
        willReturn(true).given(testDirectory).isDirectory();

        File[] directories = new File[1];
        directories[0] = testDirectory;
        willReturn(directories).given(directory).listFiles();

        File output = fileManager.getTestDirectory(directory);

        assertEquals(testDirectory, output);
    }
}
