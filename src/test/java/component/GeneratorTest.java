package component;

import fileManager.FileManager;
import fileManager.FilesWrapper;
import generator.Generator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GeneratorTest {
    private File exampleDirectory = new File("./example");
    private File exampleMainDirectory = new File(exampleDirectory.getPath() + "/src/main/java");
    private Generator generator;

    @BeforeEach
    void setup() {
        cleanup();
        FilesWrapper filesWrapper = new FilesWrapper();
        FileManager fileManager = new FileManager(filesWrapper);
        generator = new Generator(fileManager);
    }

    private void cleanup() {
        if (exampleMainDirectory.exists()) {
            for (File file : exampleMainDirectory.listFiles()) {
                if (!file.isDirectory()) {
                    file.delete();
                }
            }
            exampleMainDirectory.delete();

            for(File file: exampleDirectory.listFiles()) {
                if (file.getPath().contains("main")) {
                    file.delete();
                }
            }
        }
    }

    @Test
    void should_GenerateClass_BasedOnTestFiles() {
        generator.generate(exampleDirectory);
        String data1 = readGeneratedClass("First");
        String data2 = readGeneratedClass("Second");

        assertAll(
            () -> assertThat(data1, containsString("class First {")),
            () -> assertThat(data2, containsString("class Second {"))
        );
    }

    @Test
    void should_GenerateEmptyClass() {
        String className = "Empty";

        generator.generate(exampleDirectory);
        String data = readGeneratedClass(className);

        String expected = "class Empty {\n" +
                "}\n";

        assertEquals(expected, data);
    }

    @Test
    void should_GenerateFunctions_BasedOnTestFile() {
        String className = "First";

        generator.generate(exampleDirectory);
        String data = readGeneratedClass(className);

        assertAll(
            () -> assertThat(data, containsString("static void example() {")),
            () -> assertThat(data, containsString("static void different() {"))
        );
    }

    @Test
    void should_GenerateFunctions_WithDifferentReturns() {
        String className = "Returns";

        generator.generate(exampleDirectory);
        String data = readGeneratedClass(className);

        assertAll(
            () -> assertThat(data, containsString("static String getNullString() {")),
            () -> assertThat(data, containsString("return null")),
            () -> assertThat(data, containsString("static Integer getNullInt() {"))
        );
    }

    @Test
    void should_GenerateFunctions_WithNonNullIntReturns() {
        String className = "Returns";

        generator.generate(exampleDirectory);
        String data = readGeneratedClass(className);

        assertAll(
            () -> assertThat(data, containsString("class " + className + " {")),
            () -> assertThat(data, containsString("static Integer getInt() {")),
            () -> assertThat(data, containsString("return 7"))
        );
    }

    @Test
    void should_GenerateFunctions_WithNonNullStringReturns() {
        String className = "Returns";

        generator.generate(exampleDirectory);
        String data = readGeneratedClass(className);

        assertAll(
            () -> assertThat(data, containsString("static String getString() {")),
            () -> assertThat(data, containsString("return \"hello world\";"))
        );
    }

    @Test
    void should_GenerateFunctions_WithInput() {
        String className = "Inputs";

        generator.generate(exampleDirectory);
        String data = readGeneratedClass(className);

        assertThat(data, containsString("static void setInt(Integer arg1) {"));
    }

    @Test
    void should_GenerateFunctions_WithMultipleInputs() {
        String className = "Inputs";

        generator.generate(exampleDirectory);
        String data = readGeneratedClass(className);

        assertThat(data, containsString("static void setArgs(Integer arg1, String arg2) {"));
    }

    @Test
    void should_GenerateFunctions_WithIfElseStatements() {
        String className = "IfElse";

        generator.generate(exampleDirectory);
        String data = readGeneratedClass(className);

        List<String> expectedOrder = new ArrayList<>();

        expectedOrder.add("if (arg1 == 7) {");
        expectedOrder.add("return \"hello\";");
        expectedOrder.add("} else if (arg1 == 4) {");
        expectedOrder.add("return \"different\";");
        expectedOrder.add("} else if (arg1 == 3) {");
        expectedOrder.add("return \"bye\";");
        expectedOrder.add("else {");
        expectedOrder.add("return \"hello\";");

        orderedContainsAssert(expectedOrder, data);
    }

    private String readGeneratedClass(String className) {
        String data = "";

        try {
            data = new String(Files.readAllBytes(Paths.get(exampleMainDirectory.getPath() + "/" + className + ".java")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    private void orderedContainsAssert(List<String> expectedList, String data) {
        String[] dataLines = data.split("\n");
        int indexOfFirstMatch = getIndexOfFirstMatch(dataLines, expectedList.get(0));
        List<Executable> assertions = new ArrayList<>();

        for (int i = 0; i < expectedList.size(); i++) {
            String expectedLine = expectedList.get(i);
            String dataLine = dataLines[i  + indexOfFirstMatch];

            assertions.add(() -> assertThat(dataLine, containsString(expectedLine)));
        }

        assertAll(assertions);
    }

    private int getIndexOfFirstMatch(String[] dataLines, String expected) {
        int indexOfFirstMatch = 0;

        for (int j = 0; j < dataLines.length - 1; j++) {
            String dataLine = dataLines[j];
            if (dataLine.contains(expected)) {
                indexOfFirstMatch = j;
                break;
            }
        }
        return indexOfFirstMatch;
    }
}
