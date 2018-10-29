package javaClass;

import function.Function;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;

class ReadFileTest {
    @Test
    void should_NotGenerateFunctions_WhenNoFunctionCallsInFile() {
        File file = new File("./ExampleTest.java");
        FileReader fileReaderMock = mock(FileReader.class);

        List<Function> expectedFunctions = Collections.emptyList();
        try {
            willReturn(expectedFunctions).given(fileReaderMock).readAllLines(Paths.get(file.getPath()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JavaClass javaClass = new JavaClass(file, fileReaderMock);

        javaClass.readFile();

        assertEquals(0, javaClass.functions.size());
    }

    @Test
    void should_GenerateFunction_WhenFunctionCallInFile() {
        File file = new File("./ExampleTest.java");
        FileReader fileReaderMock = mock(FileReader.class);

        List<String> expectedFunctions = new ArrayList<>();
        expectedFunctions.add("Example.function()");
        try {
            willReturn(expectedFunctions).given(fileReaderMock).readAllLines(Paths.get(file.getPath()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JavaClass javaClass = new JavaClass(file, fileReaderMock);

        javaClass.readFile();

        Function expectedFunction = new Function();
        expectedFunction.setName("function");

        assertEquals(1, javaClass.functions.size());
        assertEquals(expectedFunction, javaClass.functions.get(0));
    }
}
