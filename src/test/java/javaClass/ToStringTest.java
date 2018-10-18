package javaClass;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToStringTest {
    @Test
    void should_PrintClass() {
        File file = new File("./ExampleTest.java");
        JavaClass javaClass = new JavaClass(file);

        String classString = javaClass.toString();

        assertEquals( "class Example {\n}\n", classString);
    }

    @Test
    void should_PrintDifferentClass() {
        File file = new File("./DifferentTest.java");
        JavaClass javaClass = new JavaClass(file);

        String classString = javaClass.toString();

        assertEquals( "class Different {\n}\n", classString);
    }
}
