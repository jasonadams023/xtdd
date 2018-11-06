package javaClass;

import function.Function;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GenerateFunctionTest {
    @Test
    void should_ReturnNil_WhenNoFunctionToBeGenerated() {
        JavaClassFactory factory = new JavaClassFactory();
        JavaClass javaClass = factory.newJavaClass("Example");

        Function output = javaClass.generateFunction("");

        assertNull(output);
    }

    @Test
    void should_ReturnFunction_WhenNewFunctionToBeGenerated() {
        JavaClassFactory factory = new JavaClassFactory();
        JavaClass javaClass = factory.newJavaClass("Example");

        Function expectedOutput = new Function();
        expectedOutput.setName("function");

        Function output = javaClass.generateFunction("Example.function()");

        assertEquals(expectedOutput, output);
    }
}
