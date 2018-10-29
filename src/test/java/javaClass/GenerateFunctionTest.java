package javaClass;

import function.Function;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GenerateFunctionTest {
    @Test
    void should_ReturnNil_WhenNoFunctionToBeGenerated() {
        File file = new File("./ExampleTest.java");

        JavaClass javaClass = new JavaClass(file);

        Function output = javaClass.generateFunction("");

        assertNull(output);
    }

    @Test
    void should_ReturnFunction_WhenNewFunctionToBeGenerated() {
        File file = new File("./ExampleTest.java");
        Function expectedOutput = new Function();
        expectedOutput.setName("function");

        JavaClass javaClass = new JavaClass(file);

        Function output = javaClass.generateFunction("Example.function()");

        assertEquals(expectedOutput, output);
    }
}
