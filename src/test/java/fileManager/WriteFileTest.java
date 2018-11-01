package fileManager;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class WriteFileTest {
    @Test
    void should_CallWriteMethodWithArgs() {
        FilesWrapper filesWrapper = mock(FilesWrapper.class);
        FileManager fileManager = new FileManager(filesWrapper);

        Path path = Paths.get("./ExamplePath.txt");
        String text = "Example text";
        byte[] bytes = text.getBytes();

        fileManager.writeFile(path, text);

        try {
            verify(filesWrapper, times(1)).write(path, bytes);
        } catch (Exception e) {
            fail("Try verify failed");
        }
    }

    @Test
    void should_PrintStackTrace_WhenExceptionThrown() {
        FilesWrapper filesWrapper = mock(FilesWrapper.class);
        FileManager fileManager = new FileManager(filesWrapper);

        Path path = Paths.get("./ExamplePath.txt");
        String text = "Example text";

        IOException exceptionMock = mock(IOException.class);

        try {
            when(filesWrapper.write(any(), any())).thenThrow(exceptionMock);
        } catch (Exception e) {
            fail("Failed in setup");
        }

        fileManager.writeFile(path, text);

        verify(exceptionMock, times(1)).printStackTrace();
    }
}
