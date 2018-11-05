package generator;

import fileManager.FileManager;
import javaClass.JavaClassFactory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.*;

class GenerateTest {
    @Test
    void should_NotCreateClassForEmptyFolder() {
        File directory = mock(File.class);
        FileManager fileManager = mock(FileManager.class);
        JavaClassFactory javaClassFactory = mock(JavaClassFactory.class);
        Generator generator = new Generator(directory, fileManager, javaClassFactory);

        int fileCount = 0;
        File[] returnedFiles = new File[fileCount];
        willReturn(returnedFiles).given(directory).listFiles();

        File testDirectory = mock(File.class);
        File[] emptyDirectory = new File[0];
        willReturn(emptyDirectory).given(testDirectory).listFiles();

        willReturn(testDirectory).given(fileManager).getTestDirectory(directory);

        generator.generate();

        verify(fileManager, times(0)).writeFile(any(), any());
    }

    @Test
    void should_CreateClassesForEachFileInFolder() {
        File directory = mock(File.class);
        String directoryString = "./example";
        willReturn(directoryString).given(directory).getPath();

        FileManager fileManager = mock(FileManager.class);
        JavaClassFactory javaClassFactory = mock(JavaClassFactory.class);
        Generator generator = new Generator(directory, fileManager, javaClassFactory);

        String firstClassName = "class1";
        String pathString1 = directoryString + "/src/" + firstClassName + ".java";
        Path filePath1 = Paths.get(pathString1);
        String fileText1 = "class class1 {\n}\n";
        File file1 = mock(File.class);
        willReturn("class1Test.java").given(file1).getName();
        willReturn(pathString1).given(file1).getPath();

        String secondClassName = "class2";
        String pathString2 = directoryString + "/src/" + secondClassName + ".java";
        Path filePath2 = Paths.get(pathString2);
        String fileText2 = "class class2 {\n}\n";
        File file2 = mock(File.class);
        willReturn("class2Test.java").given(file2).getName();
        willReturn(pathString2).given(file2).getPath();

        int fileCount = 2;
        File[] testFiles = new File[fileCount];

        testFiles[0] = file1;
        testFiles[1] = file2;

        File testDirectory = mock(File.class);
        willReturn(testFiles).given(testDirectory).listFiles();

        willReturn(testDirectory).given(fileManager).getTestDirectory(directory);

        generator.generate();

        verify(fileManager, times(1)).writeFile(filePath1, fileText1);
        verify(fileManager, times(1)).writeFile(filePath2, fileText2);
    }
}
