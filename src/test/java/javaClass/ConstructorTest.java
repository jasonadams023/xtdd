package javaClass;

import fileManager.FileManager;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class ConstructorTest {
    @Test
    void should_CreateNewJavaClassObjectWithFile() {
        File file = new File("./ExampleTest.java");
        JavaClass javaClass = new JavaClass(file);

        assertEquals(file, javaClass.file);
        assertEquals("Example", javaClass.name);
        assertEquals(0, javaClass.functions.size());
    }

    @Test
    void should_InjectFileReader() {
        File file = new File("./ExampleTest.java");
        FileManager fileManager = mock(FileManager.class);

        JavaClass javaClass = new JavaClass(file, fileManager);

        assertEquals(file, javaClass.file);
        assertEquals("Example", javaClass.name);
        assertEquals(0, javaClass.functions.size());
        assertNotNull(javaClass.fileManager);
    }
}
