package generator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CreateClassTest {
    private File directory = new File("./someDirectory");

    @Test
    void should_CallWriteFileWithClassName() {
        File testFile = new File(directory.getPath() + "/test/ExampleTest.java");
        Generator generator = new Generator(directory);
        Generator generatorSpy = spy(generator);

        willReturn("Example").given(generatorSpy).getClassName(testFile);

        List<String> output = new ArrayList<>();
        willReturn(output).given(generatorSpy).getFunctionSignatures(testFile);

        Path expectedPath = Paths.get(directory.getPath() + "/src/Example.java");
        String expectedInput = "class Example {\n}\n";

        doNothing().when(generatorSpy).writeFile(expectedPath, expectedInput);

        generatorSpy.createClass(testFile);

        verify(generatorSpy, times(1)).writeFile(expectedPath, expectedInput);
    }

    @Test
    void should_CallWriteFileWithDifferentClassName() {
        File testFile = new File(directory.getPath() + "/test/DifferentTest.java");
        Generator generator = new Generator(directory);
        Generator generatorSpy = spy(generator);

        willReturn("Different").given(generatorSpy).getClassName(testFile);

        List<String> output = new ArrayList<>();
        willReturn(output).given(generatorSpy).getFunctionSignatures(testFile);

        Path expectedPath = Paths.get(directory.getPath() + "/src/Different.java");
        String expectedInput = "class Different {\n}\n";

        doNothing().when(generatorSpy).writeFile(expectedPath, expectedInput);

        generatorSpy.createClass(testFile);

        verify(generatorSpy, times(1)).writeFile(expectedPath, expectedInput);
    }

    @Test
    void should_CallWriteFileWithMultipleFunctions() {
        File directory = new File("./someDirectory");
        File testFile = new File(directory.getPath() + "/ExampleTest.java");
        Generator generator = new Generator(directory);
        Generator generatorSpy = spy(generator);

        willReturn("Example").given(generatorSpy).getClassName(testFile);

        List<String> signatureOutput = new ArrayList<>();
        signatureOutput.add("void:one:");
        signatureOutput.add("void:two:");
        willReturn(signatureOutput).given(generatorSpy).getFunctionSignatures(testFile);

        String func1 = "static void one() {\n" +
                "}\n";
        willReturn(func1).given(generatorSpy).generateFunction(signatureOutput.get(0));
        String func2 = "static void two() {\n" +
                "}\n";
        willReturn(func2).given(generatorSpy).generateFunction(signatureOutput.get(1));

        Path expectedPath = Paths.get(directory.getPath() + "/src/Example.java");
        String expectedInput =  "class Example {\n" +
                                    "static void one() {\n}\n" +
                                    "static void two() {\n}\n" +
                                "}\n";

        doNothing().when(generatorSpy).writeFile(expectedPath, expectedInput);

        generatorSpy.createClass(testFile);

        verify(generatorSpy, times(1)).writeFile(expectedPath, expectedInput);
    }
}
