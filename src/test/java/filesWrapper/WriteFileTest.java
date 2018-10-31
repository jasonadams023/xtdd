package filesWrapper;

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
        FilesInterface filesMock = mock(FilesInterface.class);
        FilesWrapper filesWrapper = new FilesWrapper(filesMock);

        Path path = Paths.get("./ExamplePath.txt");
        String text = "Example text";
        byte[] bytes = text.getBytes();

        filesWrapper.writeFile(path, text);

        try {
            verify(filesMock, times(1)).write(path, bytes);
        } catch (Exception e) {
            fail("Try verify failed");
        }
    }

    @Test
    void should_PrintStackTrace_WhenExceptionThrown() {
        FilesInterface filesMock = mock(FilesInterface.class);
        FilesWrapper filesWrapper = new FilesWrapper(filesMock);

        Path path = Paths.get("./ExamplePath.txt");
        String text = "Example text";

        IOException exceptionMock = mock(IOException.class);

        try {
            when(filesMock.write(any(), any())).thenThrow(exceptionMock);
        } catch (Exception e) {
            fail("Failed in setup");
        }

        filesWrapper.writeFile(path, text);

        verify(exceptionMock, times(1)).printStackTrace();
    }
}
