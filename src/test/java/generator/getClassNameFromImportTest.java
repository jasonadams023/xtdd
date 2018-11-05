package generator;

import fileManager.FileManager;
import javaClass.JavaClassFactory;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

class getClassNameFromImportTest {
    @Test
    void should_ReturnNull_WhenIncorrectFormat() {
        File directory = mock(File.class);
        FileManager fileManager = mock(FileManager.class);
        JavaClassFactory javaClassFactory = mock(JavaClassFactory.class);
        Generator generator = new Generator(directory, fileManager, javaClassFactory);

        String output = generator.getClassNameFromImport("not an import line");

        assertNull(output);
    }

    @Test
    void should_ReturnNull_WhenEmptyString() {
        File directory = mock(File.class);
        FileManager fileManager = mock(FileManager.class);
        JavaClassFactory javaClassFactory = mock(JavaClassFactory.class);
        Generator generator = new Generator(directory, fileManager, javaClassFactory);

        String output = generator.getClassNameFromImport("");

        assertNull(output);
    }

    @Test
    void should_ReturnName_WhenCorrectFormat() {
        File directory = mock(File.class);
        FileManager fileManager = mock(FileManager.class);
        JavaClassFactory javaClassFactory = mock(JavaClassFactory.class);
        Generator generator = new Generator(directory, fileManager, javaClassFactory);

        String output = generator.getClassNameFromImport("import example.Example;");

        assertEquals("Example", output);
    }
}
