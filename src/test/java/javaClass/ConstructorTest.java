package javaClass;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConstructorTest {
    @Test
    void should_CreateNewJavaClassObjectWithFile() {
        File file = new File("./ExampleTest.java");
        JavaClass javaClass = new JavaClass(file);

        assertEquals(file, javaClass.file);
        assertEquals("Example", javaClass.name);
    }
}
