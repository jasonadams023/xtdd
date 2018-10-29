package javaClass;

import function.Function;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;

class ToStringTest {
    @Test
    void should_PrintEmptyClass() {
        File file = new File("./ExampleTest.java");
        JavaClass javaClass = new JavaClass(file);

        String classString = javaClass.toString();

        assertEquals( "class Example {\n}\n", classString);
    }

    @Test
    void should_PrintDifferentEmptyClass() {
        File file = new File("./DifferentTest.java");
        JavaClass javaClass = new JavaClass(file);

        String classString = javaClass.toString();

        assertEquals( "class Different {\n}\n", classString);
    }

    @Test
    void should_PrintClassWithFunctions() {
        File file = new File("./ExampleTest.java");
        JavaClass javaClass = new JavaClass(file);

        List<Function> functions = new ArrayList<>();

        Function mockFunction = mock(Function.class);
        willReturn("void function() {\n}\n").given(mockFunction).toString();

        functions.add(mockFunction);
        javaClass.functions = functions;

        String expected = "class Example {\n" +
                "void function() {\n}\n" +
                "}\n";

        String classString = javaClass.toString();

        assertEquals(expected, classString);
    }
}
