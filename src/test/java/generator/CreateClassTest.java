package generator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CreateClassTest {
    private File exampleDirectory = new File("./example");
    private File exampleSourceDirectory = new File(exampleDirectory.getPath() + "/src");

    @AfterAll
    @BeforeAll
    void cleanup() {
        File directory = exampleSourceDirectory;
        for(File file: directory.listFiles()) {
            if (!file.isDirectory()) {
                file.delete();
            }
        }
    }

    @Test
    void shouldWriteToFolder() {
        String className = "Example";
        File testFile = new File(exampleDirectory.getPath() + "/test/" + className + "Test.java");

        Generator generator = new Generator(exampleDirectory);
        Generator generatorSpy = spy(generator);
        List<String> output = new ArrayList<>();
        willReturn(output).given(generatorSpy).getFunctionSignatures(testFile);

        generatorSpy.createClass(testFile);

        assertTrue(exampleSourceDirectory.list().length > 0);
    }

    @Test
    void shouldCreateJavaFile() {
        String className = "Example";
        File testFile = new File(exampleDirectory.getPath() + "/test/" + className + "Test.java");

        Generator generator = new Generator(exampleDirectory);
        Generator generatorSpy = spy(generator);
        List<String> output = new ArrayList<>();
        willReturn(output).given(generatorSpy).getFunctionSignatures(testFile);

        generatorSpy.createClass(testFile);
        File file = new File(exampleSourceDirectory.getPath() + "/Example.java");
        assertTrue(file.exists());
    }

    @Test
    void shouldGenerateClass() {
        String className = "Example";
        File testFile = new File(exampleDirectory.getPath() + "/test/" + className + "Test.java");

        Generator generator = new Generator(exampleDirectory);
        Generator generatorSpy = spy(generator);
        List<String> output = new ArrayList<>();
        willReturn(output).given(generatorSpy).getFunctionSignatures(testFile);

        generatorSpy.createClass(testFile);

        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(exampleSourceDirectory.getPath() + "/" + className + ".java")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(data.contains("class " + className + " {"));
    }

    @Test
    void shouldGenerateDifferentClass() {
        String className = "Different";
        File testFile = new File(exampleDirectory.getPath() + "/test/" + className + "Test.java");

        Generator generator = new Generator(exampleDirectory);
        Generator generatorSpy = spy(generator);
        List<String> output = new ArrayList<>();
        willReturn(output).given(generatorSpy).getFunctionSignatures(testFile);

        generatorSpy.createClass(testFile);

        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(exampleSourceDirectory.getPath() + "/" + className + ".java")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(data.contains("class " + className + " {"));
    }

    @Test
    void should_CallWriteFileWithClassName() {
        File testFile = new File(exampleDirectory.getPath() + "/test/ExampleTest.java");
        Generator generator = new Generator(exampleDirectory);
        Generator generatorSpy = spy(generator);

        willReturn("Example").given(generatorSpy).getClassName(testFile);

        List<String> output = new ArrayList<>();
        willReturn(output).given(generatorSpy).getFunctionSignatures(testFile);

        Path expectedPath = Paths.get(exampleDirectory.getPath() + "/src/Example.java");
        String expectedInput = "class Example {\n}\n";

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
