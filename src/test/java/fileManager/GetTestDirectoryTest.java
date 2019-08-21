package fileManager;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;

class GetTestDirectoryTest {
    @Test
    void should_ReturnNull_WhenNoTestDirectoryExists() {
        FilesWrapper filesWrapper = mock(FilesWrapper.class);
        FileManager fileManager = new FileManager(filesWrapper);
        File directory = mock(File.class);

        File output = fileManager.getTestDirectory(directory);

        assertNull(output);
    }

    @Test
    void should_ReturnNull_WhenTestFileNotDirectory() {
        FilesWrapper filesWrapper = mock(FilesWrapper.class);
        FileManager fileManager = new FileManager(filesWrapper);
        File directory = mock(File.class);

        File testDirectory = mock(File.class);
        willReturn("test").given(testDirectory).getName();
        willReturn(false).given(testDirectory).isDirectory();

        File[] directories = new File[1];
        directories[0] = testDirectory;
        willReturn(directories).given(directory).listFiles();

        File output = fileManager.getTestDirectory(directory);

        assertNull(output);
    }

    @Test
    void should_ReturnTestDirectory_WhenItExistsAndIsNested() {
        FilesWrapper filesWrapper = mock(FilesWrapper.class);
        FileManager fileManager = new FileManager(filesWrapper);

        File mainDirectory = mock(File.class);
        willReturn("main").given(mainDirectory).getName();
        willReturn(true).given(mainDirectory).isDirectory();

        File testDirectory = mock(File.class);
        willReturn("test").given(testDirectory).getName();
        willReturn(true).given(testDirectory).isDirectory();

        File srcDirectory = mock(File.class);
        willReturn("src").given(srcDirectory).getName();
        willReturn(true).given(srcDirectory).isDirectory();
        File[] srcDirectories = new File[2];
        srcDirectories[0] = mainDirectory;
        srcDirectories[1] = testDirectory;
        willReturn(srcDirectories).given(srcDirectory).listFiles();

        File exampleDirectory = mock(File.class);
        willReturn("src").given(exampleDirectory).getName();
        willReturn(true).given(exampleDirectory).isDirectory();
        File[] exampleDirectories = new File[1];
        exampleDirectories[0] = srcDirectory;
        willReturn(exampleDirectories).given(exampleDirectory).listFiles();

        File output = fileManager.getTestDirectory(exampleDirectory);

        assertEquals(testDirectory, output);
    }
}
