package component;

import javaClass.JavaClass;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JavaClassTest {
    @Test
    void should_ReturnClassAsString() {
        File file = new File("./ExampleTest.java");
        JavaClass javaClass = new JavaClass(file);

        String classString = javaClass.toString();

        assertEquals( "class Example {\n}\n", classString);
    }
}
