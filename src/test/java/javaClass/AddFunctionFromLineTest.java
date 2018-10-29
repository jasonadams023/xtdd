package javaClass;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddFunctionFromLineTest {
    @Test
    void should_NotAddFunction_WhenLineDoesNotContainNewFunction() {
        File file = new File("./ExampleTest.java");
        String line = "someLine";

        JavaClass javaClass = new JavaClass(file);

        javaClass.addFunctionFromLine(line);

        assertEquals(0, javaClass.functions.size());
    }

    @Test
    void should_AddFunction_WhenLineDoesContainNewFunction() {
        File file = new File("./ExampleTest.java");
        String line = "Example.function();";

        JavaClass javaClass = new JavaClass(file);

        javaClass.addFunctionFromLine(line);

        assertEquals(1, javaClass.functions.size());
    }
}
