package javaClass;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetNameTest {
    @Test
    void should_ReturnName() {
        File file = new File("./ExampleTest.java");
        JavaClass javaClass = new JavaClass(file);

        String name = javaClass.getName();

        assertEquals("Example", name);
    }
}
