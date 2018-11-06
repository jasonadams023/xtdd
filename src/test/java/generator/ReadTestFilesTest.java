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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ReadTestFilesTest {
    @Test
    void should_ReadFilesFromTestDirectory() {
        File directory = mock(File.class);
        FileManager fileManager = mock(FileManager.class);
        JavaClassFactory javaClassFactory = mock(JavaClassFactory.class);
        Generator generator = new Generator(directory, fileManager, javaClassFactory);

        Path path1 = mock(Path.class);
        Path path2 = mock(Path.class);

        JavaClass expectedClass1 = mock(JavaClass.class);
        willReturn("Expected").given(expectedClass1).getName();
        JavaClass expectedClass2 = mock(JavaClass.class);
        willReturn("Different").given(expectedClass2).getName();

        List<String> fileLines1 = new ArrayList<>();
        fileLines1.add(Generator.startFlag);
        fileLines1.add("import example.Example;");
        fileLines1.add(Generator.endFlag);

        List<String> fileLines2 = new ArrayList<>();
        fileLines2.add(Generator.startFlag);
        fileLines2.add("import example.Different;");
        fileLines2.add(Generator.endFlag);

        File testDirectory = mock(File.class);

        File[] testFiles = new File[2];
        File testFile1 = mock(File.class);
        testFiles[0] = testFile1;
        File testFile2 = mock(File.class);
        testFiles[1] = testFile2;

        willReturn(testDirectory).given(fileManager).getTestDirectory(directory);
        willReturn(testFiles).given(testDirectory).listFiles();

        willReturn(path1).given(testFile1).toPath();
        willReturn(path2).given(testFile2).toPath();

        willReturn(fileLines1).given(fileManager).readAllLines(path1);
        willReturn(fileLines2).given(fileManager).readAllLines(path2);

        willDoNothing().given(expectedClass1).createFunctionsFromPath(any());
        willDoNothing().given(expectedClass2).createFunctionsFromPath(any());

        willReturn(expectedClass1).given(javaClassFactory).newJavaClass("Example");
        willReturn(expectedClass2).given(javaClassFactory).newJavaClass("Different");

        generator.readFiles();

        assertEquals(2, generator.javaClasses.size());
        assertEquals(expectedClass1, generator.javaClasses.get(0));
        verify(expectedClass1, times(1)).createFunctionsFromPath(path1);
        verify(expectedClass1, times(1)).createFunctionsFromPath(path2);
        assertEquals(expectedClass2, generator.javaClasses.get(1));
        verify(expectedClass2, times(1)).createFunctionsFromPath(path1);
        verify(expectedClass2, times(1)).createFunctionsFromPath(path2);
    }
}
