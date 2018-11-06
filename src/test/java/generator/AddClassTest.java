package generator;

import fileManager.FileManager;
import javaClass.JavaClass;
import javaClass.JavaClassFactory;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;

class AddClassTest {
    @Test
    void should_NotAddClass_WhenClassNameIsNull() {
        File directory = mock(File.class);
        FileManager fileManager = mock(FileManager.class);
        JavaClassFactory javaClassFactory = mock(JavaClassFactory.class);
        Generator generator = new Generator(directory, fileManager, javaClassFactory);

        generator.addClass(null);

        assertEquals(0, generator.javaClasses.size());
    }

    @Test
    void should_AddClass_WhenClassNameIsValid() {
        File directory = mock(File.class);
        FileManager fileManager = mock(FileManager.class);
        JavaClassFactory javaClassFactory = mock(JavaClassFactory.class);
        Generator generator = new Generator(directory, fileManager, javaClassFactory);

        JavaClass javaClass = new JavaClass("Example", fileManager);
        willReturn(javaClass).given(javaClassFactory).newJavaClass("Example");

        generator.addClass("Example");

        assertEquals(1, generator.javaClasses.size());
        assertEquals("Example", generator.javaClasses.get(0).getName());
    }

    @Test
    void should_NotAddDuplicateClass() {
        File directory = mock(File.class);
        FileManager fileManager = mock(FileManager.class);
        JavaClassFactory javaClassFactory = mock(JavaClassFactory.class);
        Generator generator = new Generator(directory, fileManager, javaClassFactory);

        generator.javaClasses.add(new JavaClass("Example", fileManager));

        generator.addClass("Example");

        assertEquals(1, generator.javaClasses.size());
    }
}
