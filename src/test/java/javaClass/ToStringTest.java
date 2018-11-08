package javaClass;

import fileManager.FileManager;
import function.Function;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.*;

class ToStringTest {
    @Test
    void should_PrintEmptyClass() {
        FileManager fileManager = mock(FileManager.class);
        JavaClass javaClass = new JavaClass("Class", fileManager);

        String expected = "class Class {\n}\n";

        String output = javaClass.toString();

        assertEquals(expected, output);
    }

    @Test
    void should_PrintClass_WithFunctions() {
        FileManager fileManager = mock(FileManager.class);
        JavaClass javaClass = new JavaClass("Class", fileManager);

        Function function = new Function("function", "void");
        javaClass.functions.add(function);

        String expected = "class Class {\n" +
                "static void function() {\n}\n" +
                "}\n";

        String output = javaClass.toString();

        assertEquals(expected, output);
    }
}
