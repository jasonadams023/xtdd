package generator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GenerateFunctionTest {
    File exampleDirectory = new File("./example");

    @Test
    void should_ReturnStringForVoidFunction_When_GivenVoidSignature() {
        Generator generator = new Generator(exampleDirectory);
        String signature = "void:example:";

        String output = generator.generateFunction(signature);

        String expected = "static void example() {\n" +
                "}\n";

        assertEquals(expected, output);
    }

    @Test
    void should_ReturnDifferentString_When_GivenDifferentSignature() {
        Generator generator = new Generator(exampleDirectory);
        String signature = "String:different:";

        String output = generator.generateFunction(signature);

        String expected = "static String different() {\n" +
                "}\n";

        assertEquals(expected, output);
    }
}
