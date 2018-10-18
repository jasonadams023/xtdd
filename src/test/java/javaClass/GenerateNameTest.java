package javaClass;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenerateNameTest {
    @Test
    void should_ReturnClassName() {
        File file = new File("./ExampleTest.java");
        JavaClass javaClass = new JavaClass(file);

        String output = javaClass.generateName();

        assertEquals("Example", output);
    }

    @Test
    void should_ReturnDifferentClassName() {
        File file = new File("./DifferentTest.java");
        JavaClass javaClass = new JavaClass(file);

        String output = javaClass.generateName();

        assertEquals("Different", output);
    }
}
