package generator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GetFunctionSignaturesTest {
    private String testDirectoryString = "./example/test/";

    @Test
    void should_ReturnEmptyStringArray_When_ThereAreNoFunctions() {
        File testFile = new File(testDirectoryString + "EmptyTest.java");
        Generator generator = new Generator(testFile);

        List<String> output = generator.getFunctionSignatures(testFile);

        assertEquals(0, output.size());
    }

    @Test
    void should_ReturnStringArrayWithSignatures_When_ThereAreFunctions() {
        File testFile = new File(testDirectoryString + "FirstTest.java");
        Generator generator = new Generator(testFile);

        List<String> output = generator.getFunctionSignatures(testFile);

        String expected = "void:example:";
        String expected2 = "void:different:";

        assertEquals(expected, output.get(0));
        assertEquals(expected2, output.get(1));
    }
}
