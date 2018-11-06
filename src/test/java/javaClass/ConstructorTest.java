package javaClass;

import fileManager.FileManager;
import function.Function;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class ConstructorTest {
    @Test
    void should_CreateNewJavaClass_WithNameAndFileManager() {
        FileManager fileManager = mock(FileManager.class);
        JavaClass javaClass = new JavaClass("Example", fileManager);

        assertEquals("Example", javaClass.name);
        assertEquals(new ArrayList<Function>(), javaClass.functions);
        assertNotNull(javaClass.fileManager);
    }
}
