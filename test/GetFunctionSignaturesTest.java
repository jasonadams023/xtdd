import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GetFunctionSignaturesTest {
    private String testDirectoryString = "./example/test/";

    @Test
    void should_ReturnEmptyStringArray_When_ThereAreNoFunctions() {
        File testFile = new File(testDirectoryString + "EmptyTest.java");

        List<String> output = Generator.getFunctionSignatures(testFile);

        assertEquals(0, output.size());
    }

    @Test
    void should_ReturnStringArrayWithSignatures_When_ThereAreFunctions() {
        File testFile = new File(testDirectoryString + "FirstTest.java");

        List<String> output = Generator.getFunctionSignatures(testFile);

        String expected = "void:example:";
        String expected2 = "void:different:";

        assertEquals(expected, output.get(0));
        assertEquals(expected2, output.get(1));
    }
}
