package generator;

import fileManager.FileManager;
import javaClass.JavaClass;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;

class CreateClassesTest {
    @Test
    void should_NotGenerateClasses_WhenNoClassesFlagged() {
        File directoryMock = mock(File.class);
        FileManager fileManager = mock(FileManager.class);
        Generator generator = new Generator(directoryMock, fileManager);

        generator.createClasses();

        assertEquals(0, generator.javaClasses.size());
    }

    @Test
    void should_GenerateClasses_WhenClassesFlagged() {
        File directoryMock = mock(File.class);
        FileManager fileManager = mock(FileManager.class);
        Generator generator = new Generator(directoryMock, fileManager);

        List<String> lines = new ArrayList<>();
        lines.add("package example;");
        lines.add(Generator.startFlag);
        lines.add("import example.Example;");
        lines.add("import example.Different;");
        lines.add(Generator.endFlag);
        lines.add("class Test {");
        willReturn(lines).given(fileManager).readAllLines(any());

        JavaClass exampleClass = new JavaClass("Example");
        JavaClass differentClass = new JavaClass("Different");

        generator.createClasses();

        assertEquals(2, generator.javaClasses.size());
        assertTrue(generator.javaClasses.contains(exampleClass));
        assertTrue(generator.javaClasses.contains(differentClass));
    }
}
