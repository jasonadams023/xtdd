package javaClass;

import fileManager.FileManager;
import function.Function;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;

class CreateFunctionsFromPathTest {
    @Test
    void should_NotGenerateFunctionsFromGivenPath_WhenNoFunctionCallsInFile() {
        FileManager fileManager = mock(FileManager.class);
        Path path = mock(Path.class);

        List<String> expectedLines = new ArrayList<>();
        expectedLines.add("class Example{\n");
        expectedLines.add("}\n");
        try {
            willReturn(expectedLines).given(fileManager).readAllLines(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JavaClass javaClass = new JavaClass("Example", fileManager);

        javaClass.createFunctionsFromPath(path);

        assertEquals(0, javaClass.functions.size());
    }

    @Test
    void should_NotGenerateFunctionsFromGivenPath_WhenClassNameDoesNotMatch() {
        FileManager fileManager = mock(FileManager.class);
        Path path = mock(Path.class);

        List<String> expectedLines = new ArrayList<>();
        expectedLines.add("Different.function()");
        try {
            willReturn(expectedLines).given(fileManager).readAllLines(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JavaClass javaClass = new JavaClass("Example", fileManager);

        javaClass.createFunctionsFromPath(path);

        assertEquals(0, javaClass.functions.size());
    }

    @Test
    void should_GenerateFunctionFromGivenPath_WhenFunctionCallInFile() {
        FileManager fileManager = mock(FileManager.class);
        Path path = mock(Path.class);

        List<String> expectedLines = new ArrayList<>();
        expectedLines.add("Example.function()");
        try {
            willReturn(expectedLines).given(fileManager).readAllLines(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JavaClass javaClass = new JavaClass("Example", fileManager);

        javaClass.createFunctionsFromPath(path);

        Function expectedFunction = new Function("function");

        assertEquals(1, javaClass.functions.size());
        assertEquals(expectedFunction, javaClass.functions.get(0));
    }
}
