package fileManager;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class WriteFileTest {
    @Test
    void should_CallWriteMethodWithArgs() throws IOException {
        FilesWrapper filesWrapper = mock(FilesWrapper.class);
        FileManager fileManager = new FileManager(filesWrapper);

        Path path = Paths.get("./ExamplePath.txt");
        String text = "Example text";
        byte[] bytes = text.getBytes();

        fileManager.writeFile(path, text);

        verify(filesWrapper, times(1)).write(path, bytes);
    }

    @Test
    void should_PrintStackTrace_WhenExceptionThrown() throws IOException {
        FilesWrapper filesWrapper = mock(FilesWrapper.class);
        FileManager fileManager = new FileManager(filesWrapper);

        Path path = Paths.get("./ExamplePath.txt");
        String text = "Example text";

        IOException exceptionMock = mock(IOException.class);

        when(filesWrapper.write(any(), any())).thenThrow(exceptionMock);

        fileManager.writeFile(path, text);

        verify(exceptionMock, times(1)).printStackTrace();
    }
}
