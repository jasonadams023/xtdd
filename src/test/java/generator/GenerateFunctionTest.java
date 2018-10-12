package generator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GenerateFunctionTest {
    @Test
    void should_ReturnStringForVoidFunction_When_GivenVoidSignature() {
        String signature = "void:example:";

        String output = Generator.generateFunction(signature);

        String expected = "static void example() {\n" +
                "}\n";

        assertEquals(expected, output);
    }

    @Test
    void should_ReturnDifferentString_When_GivenDifferentSignature() {
        String signature = "String:different:";

        String output = Generator.generateFunction(signature);

        String expected = "static String different() {\n" +
                "}\n";

        assertEquals(expected, output);
    }
}
