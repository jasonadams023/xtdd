package javaClass;

import function.Function;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToStringTest {
    @Test
    void should_PrintEmptyClass() {
        JavaClass javaClass = new JavaClass("Class");

        String expected = "class Class {\n}\n";

        String output = javaClass.toString();

        assertEquals(expected, output);
    }

    @Test
    void should_PrintClass_WithFunctions() {
        JavaClass javaClass = new JavaClass("Class");

        Function function = new Function("function", "void");
        javaClass.functions.add(function);

        String expected = "class Class {\n" +
                "static void function() {\n}\n" +
                "}\n";

        String output = javaClass.toString();

        assertEquals(expected, output);
    }
}
