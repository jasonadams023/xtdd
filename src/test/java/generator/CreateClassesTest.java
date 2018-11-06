package generator;

import fileManager.FileManager;
import javaClass.JavaClass;
import javaClass.JavaClassFactory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;

class CreateClassesTest {
    @Test
    void should_NotGenerateClasses_WhenNoClassesFlagged() {
        File directory = mock(File.class);
        FileManager fileManager = mock(FileManager.class);
        JavaClassFactory javaClassFactory = mock(JavaClassFactory.class);
        Generator generator = new Generator(directory, fileManager, javaClassFactory);
        Path path = mock(Path.class);

        generator.createClasses(path);

        assertEquals(0, generator.javaClasses.size());
    }

    @Test
    void should_GenerateClasses_WhenClassesFlagged() {
        File directory = mock(File.class);
        FileManager fileManager = mock(FileManager.class);
        JavaClassFactory javaClassFactory = mock(JavaClassFactory.class);
        Generator generator = new Generator(directory, fileManager, javaClassFactory);
        Path path = mock(Path.class);

        List<String> lines = new ArrayList<>();
        lines.add("package example;");
        lines.add(Generator.startFlag);
        lines.add("import example.Example;");
        lines.add("import example.Different;");
        lines.add(Generator.endFlag);
        lines.add("class Test {");
        willReturn(lines).given(fileManager).readAllLines(path);

        JavaClass exampleClass = new JavaClass("Example");
        JavaClass differentClass = new JavaClass("Different");

        willReturn(exampleClass).given(javaClassFactory).newJavaClass("Example");
        willReturn(differentClass).given(javaClassFactory).newJavaClass("Different");

        generator.createClasses(path);

        assertEquals(2, generator.javaClasses.size());
        assertTrue(generator.javaClasses.contains(exampleClass));
        assertTrue(generator.javaClasses.contains(differentClass));
    }
}
