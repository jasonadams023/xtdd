package generator;

import fileManager.FileManager;
import javaClass.JavaClass;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class AddClassTest {
    @Test
    void should_NotAddClass_WhenClassNameIsNull() {
        File directory = mock(File.class);
        FileManager fileManager = mock(FileManager.class);
        Generator generator = new Generator(directory, fileManager);

        generator.addClass(null);

        assertEquals(0, generator.javaClasses.size());
    }

    @Test
    void should_AddClass_WhenClassNameIsValid() {
        File directory = mock(File.class);
        FileManager fileManager = mock(FileManager.class);
        Generator generator = new Generator(directory, fileManager);

        generator.addClass("Example");

        assertEquals(1, generator.javaClasses.size());
        assertEquals("Example", generator.javaClasses.get(0).getName());
    }

    @Test
    void should_NotAddDuplicateClass() {
        File directory = mock(File.class);
        FileManager fileManager = mock(FileManager.class);
        Generator generator = new Generator(directory, fileManager);

        generator.javaClasses.add(new JavaClass("Example", fileManager));

        generator.addClass("Example");

        assertEquals(1, generator.javaClasses.size());
    }
}
