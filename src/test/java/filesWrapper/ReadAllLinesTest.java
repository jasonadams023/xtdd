package filesWrapper;

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
    void should_CallReadAllLinesMethod() {
        FilesInterface filesMock = mock(FilesInterface.class);
        FilesWrapper filesWrapper = new FilesWrapper(filesMock);

        Path path = Paths.get("./somePath");
        List<String> lines = new ArrayList<>();
        lines.add("one");
        lines.add("two");

        try {
            willReturn(lines).given(filesMock).readAllLines(path);
        } catch (Exception e) {
            fail("Failed in setup");
        }

        List<String> output = filesWrapper.readAllLines(path);

        assertEquals(lines, output);
    }

    @Test
    void should_PrintStackTraceAndReturnEmptyList_WhenExceptionThrown() {
        FilesInterface filesMock = mock(FilesInterface.class);
        FilesWrapper filesWrapper = new FilesWrapper(filesMock);
        Path path = Paths.get("./somePath");

        IOException exceptionMock = mock(IOException.class);

        try {
            when(filesMock.readAllLines(path)).thenThrow(exceptionMock);
        } catch (Exception e) {
            fail("Failed in setup");
        }

        List<String> output = filesWrapper.readAllLines(path);

        verify(exceptionMock, times(1)).printStackTrace();
        assertEquals(new ArrayList<String>(), output);
    }
}
