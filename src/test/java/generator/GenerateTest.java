package generator;

import fileManager.FileManager;
import javaClass.JavaClassFactory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.*;

class GenerateTest {
    @Test
    void should_NotGenerateFilesFromTests_WhenNothingToGenerate() {
        File directory = mock(File.class);
        FileManager fileManager = mock(FileManager.class);
        JavaClassFactory javaClassFactory = new JavaClassFactory(fileManager);
        Generator generator = new Generator(directory, fileManager, javaClassFactory);

        File testDirectory = mock(File.class);
        willReturn(testDirectory).given(fileManager).getTestDirectory(directory);

        File[] testFiles = new File[1];
        File testFile = mock(File.class);
        testFiles[0] = testFile;
        willReturn(testFiles).given(testDirectory).listFiles();

        Path testPath = mock(Path.class);
        willReturn(testPath).given(testFile).toPath();

        List<String> readLines = new ArrayList<>();
        readLines.add("Example.function();");
        willReturn(readLines).given(fileManager).readAllLines(testPath);

        willReturn("./example").given(directory).getPath();

        generator.generate();

        verify(fileManager, times(0)).writeFile(any(), any());
    }

    @Test
    void should_GenerateFilesFromTests() {
        File directory = mock(File.class);
        FileManager fileManager = mock(FileManager.class);
        JavaClassFactory javaClassFactory = new JavaClassFactory(fileManager);
        Generator generator = new Generator(directory, fileManager, javaClassFactory);

        File testDirectory = mock(File.class);
        willReturn(testDirectory).given(fileManager).getTestDirectory(directory);

        File[] testFiles = new File[1];
        File testFile = mock(File.class);
        testFiles[0] = testFile;
        willReturn(testFiles).given(testDirectory).listFiles();

        Path testPath = mock(Path.class);
        willReturn(testPath).given(testFile).toPath();

        List<String> readLines = new ArrayList<>();
        readLines.add(Generator.startFlag);
        readLines.add("import example.Example;");
        readLines.add(Generator.endFlag);
        readLines.add("Example.function();");
        willReturn(readLines).given(fileManager).readAllLines(testPath);

        willReturn("./example").given(directory).getPath();

        Path expectedPath = Paths.get("./example/src/Example.java");
        String expectedString = "class Example {\n" +
                "static void function() {\n" +
                "}\n" +
                "}\n";

        generator.generate();

        verify(fileManager, times(1)).writeFile(expectedPath, expectedString);
    }
}
