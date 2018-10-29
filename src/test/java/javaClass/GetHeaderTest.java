package javaClass;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetHeaderTest {
    @Test
    void should_ReturnHeader() {
        File file = new File("./ExampleTest.java");
        JavaClass javaClass = new JavaClass(file);

        String header = javaClass.getHeader();

        assertEquals("class Example {\n", header);
    }
}
