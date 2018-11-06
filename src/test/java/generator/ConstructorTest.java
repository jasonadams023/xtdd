package generator;

import fileManager.FileManager;
import javaClass.JavaClass;
import javaClass.JavaClassFactory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class ConstructorTest {
    @Test
    void should_ReturnNewGenerator() {
        File directory = mock(File.class);
        FileManager fileManager = mock(FileManager.class);
        JavaClassFactory javaClassFactory = mock(JavaClassFactory.class);
        Generator generator = new Generator(directory, fileManager, javaClassFactory);

        assertNotNull(generator);
        assertEquals(fileManager, generator.fileManager);
        assertEquals(directory, generator.directory);
        assertEquals(new ArrayList<JavaClass>(), generator.javaClasses);
        assertEquals(javaClassFactory, generator.javaClassFactory);
    }
}
