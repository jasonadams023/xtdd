package fileManager;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.*;

class ReadAllLinesTest {
    @Test
    void should_CallReadAllLinesMethod() throws IOException {
        FilesWrapper filesWrapper = mock(FilesWrapper.class);
        FileManager fileManager = new FileManager(filesWrapper);

        Path path = Paths.get("./somePath");
        List<String> lines = new ArrayList<>();
        lines.add("one");
        lines.add("two");

        willReturn(lines).given(filesWrapper).readAllLines(path);

        List<String> output = fileManager.readAllLines(path);

        assertEquals(lines, output);
    }

    @Test
    void should_PrintStackTraceAndReturnEmptyList_WhenExceptionThrown() throws IOException {
        FilesWrapper filesWrapper = mock(FilesWrapper.class);
        FileManager fileManager = new FileManager(filesWrapper);
        Path path = Paths.get("./somePath");

        IOException exceptionMock = mock(IOException.class);

        when(filesWrapper.readAllLines(path)).thenThrow(exceptionMock);

        List<String> output = fileManager.readAllLines(path);

        verify(exceptionMock, times(1)).printStackTrace();
        assertEquals(new ArrayList<String>(), output);
    }
}
