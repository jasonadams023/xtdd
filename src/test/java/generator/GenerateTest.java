package generator;

import fileManager.FileManager;
import javaClass.JavaClassFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.*;

class GenerateTest {
    private FileManager fileManager;
    private Generator generator;
    private Path testPath;
    private Path testPath2;

    @BeforeEach
    void setup() {
        File directory = mock(File.class);
        fileManager = mock(FileManager.class);
        JavaClassFactory javaClassFactory = new JavaClassFactory(fileManager);
        generator = new Generator(directory, fileManager, javaClassFactory);

        File testDirectory = mock(File.class);
        willReturn(testDirectory).given(fileManager).getTestDirectory(directory);

        File[] testFiles = new File[2];
        File testFile = mock(File.class);
        testFiles[0] = testFile;
        File testFile2 = mock(File.class);
        testFiles[1] = testFile2;
        willReturn(testFiles).given(testDirectory).listFiles();

        testPath = mock(Path.class);
        willReturn(testPath).given(testFile).toPath();

        testPath2 = mock(Path.class);
        willReturn(testPath2).given(testFile2).toPath();

        List<String> readLines = new ArrayList<>();
        readLines.add(Generator.startFlag);
        readLines.add("import example.Example;");
        readLines.add(Generator.endFlag);
        readLines.add("Example.function();");
        willReturn(readLines).given(fileManager).readAllLines(testPath);

        List<String> readLines2 = new ArrayList<>();
        readLines2.add(Generator.startFlag);
        readLines2.add("import example.Different;");
        readLines2.add(Generator.endFlag);
        readLines2.add("String output = Different.otherFunction();");
        willReturn(readLines2).given(fileManager).readAllLines(testPath2);

        willReturn("./example").given(directory).getPath();
    }

    @Test
    void should_NotGenerateFilesFromTests_WhenNothingToGenerate() {
        List<String> readLines = new ArrayList<>();
        readLines.add("Example.function();");
        willReturn(readLines).given(fileManager).readAllLines(testPath);
        willReturn(readLines).given(fileManager).readAllLines(testPath2);

        generator.generate();

        verify(fileManager, times(0)).writeFile(any(), any());
    }

    @Test
    void should_GenerateFilesFromTests() {
        generator.generate();

        verify(fileManager, times(2)).writeFile(any(), any());
    }
}
