package javaClass;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.*;

class GetLinesTest {
    @Test
    void should_ReturnLines() {
        File file = new File("./ExampleTest.java");
        FileReader fileReaderMock = mock(FileReader.class);

        List<String> expectedLines = new ArrayList<>();
        expectedLines.add("class Example{\n");
        expectedLines.add("}\n");
        try {
            willReturn(expectedLines).given(fileReaderMock).readAllLines(Paths.get(file.getPath()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JavaClass javaClass = new JavaClass(file, fileReaderMock);

        List<String> lines = javaClass.getLines();

        assertEquals(expectedLines, lines);
    }

    @Test
    void should_ReturnEmptyList() {
        File file = new File("./ExampleTest.java");
        FileReader fileReaderMock = mock(FileReader.class);

        List<String> expectedLines = new ArrayList<>();
        IOException exceptionMock = mock(IOException.class);

        try {
            when(fileReaderMock.readAllLines(any())).thenThrow(exceptionMock);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JavaClass javaClass = new JavaClass(file, fileReaderMock);

        List<String> lines = javaClass.getLines();

        assertEquals(expectedLines, lines);
        verify(exceptionMock, times(1)).printStackTrace();
    }
}
