package javaClass;

import filesWrapper.FilesWrapper;
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
        FilesWrapper filesWrapper = mock(FilesWrapper.class);

        JavaClass javaClass = new JavaClass(file, filesWrapper);

        assertEquals(file, javaClass.file);
        assertEquals("Example", javaClass.name);
        assertEquals(0, javaClass.functions.size());
        assertNotNull(javaClass.filesWrapper);
    }
}
