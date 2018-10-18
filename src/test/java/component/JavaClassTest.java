package component;

import javaClass.JavaClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class JavaClassTest {
    @Test
    void should_CreateNewJavaClassObject() {
        JavaClass javaClass = new JavaClass();

        assertNotNull(javaClass);
    }
}
