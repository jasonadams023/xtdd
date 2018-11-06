package javaClass;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddFunctionFromLineTest {
    @Test
    void should_NotAddFunction_WhenLineDoesNotContainNewFunction() {
        JavaClassFactory factory = new JavaClassFactory();
        JavaClass javaClass = factory.newJavaClass("Example");

        String line = "someLine";

        javaClass.addFunctionFromLine(line);

        assertEquals(0, javaClass.functions.size());
    }

    @Test
    void should_AddFunction_WhenLineDoesContainNewFunction() {
        JavaClassFactory factory = new JavaClassFactory();
        JavaClass javaClass = factory.newJavaClass("Example");

        String line = "Example.function();";

        javaClass.addFunctionFromLine(line);

        assertEquals(1, javaClass.functions.size());
    }
}
