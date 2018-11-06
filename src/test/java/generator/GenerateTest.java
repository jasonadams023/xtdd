package generator;

import fileManager.FileManager;
import javaClass.JavaClassFactory;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.mockito.Mockito.*;

class GenerateTest {
    @Test
    void should_ReadAndWriteFiles() {
        File directory = mock(File.class);
        FileManager fileManager = mock(FileManager.class);
        JavaClassFactory javaClassFactory = mock(JavaClassFactory.class);
        Generator generator = new Generator(directory, fileManager, javaClassFactory);
        Generator generatorSpy = spy(generator);

        doNothing().when(generatorSpy).readFiles();
        doNothing().when(generatorSpy).writeFiles();

        generatorSpy.generate();

        verify(generatorSpy, times(1)).readFiles();
        verify(generatorSpy, times(1)).writeFiles();
    }
}
