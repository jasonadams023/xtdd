package generator;

import fileManager.FileManager;
import javaClass.JavaClass;
import javaClass.JavaClassFactory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class PopulateClassesTest {
    @Test
    void should_RunCreateFunctionsFromPathForEachClass() {
        File directory = mock(File.class);
        FileManager fileManager = mock(FileManager.class);
        JavaClassFactory javaClassFactory = mock(JavaClassFactory.class);
        Generator generator = new Generator(directory, fileManager, javaClassFactory);
        Path path = mock(Path.class);

        List<JavaClass> javaClasses = new ArrayList<>();
        JavaClass classMock1 = mock(JavaClass.class);
        javaClasses.add(classMock1);
        JavaClass classMock2 = mock(JavaClass.class);
        javaClasses.add(classMock2);

        generator.javaClasses = javaClasses;

        generator.populateClasses(path);

        verify(classMock1, times(1)).createFunctionsFromPath(path);
        verify(classMock2, times(1)).createFunctionsFromPath(path);
    }
}
