package component;

import javaClass.JavaClass;
import javaClass.JavaClassFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JavaClassTest {
    @Test
    void should_ReturnClassAsString() {
        JavaClassFactory factory = new JavaClassFactory();
        JavaClass javaClass = factory.newJavaClass("Example");

        String classString = javaClass.toString();

        assertEquals( "class Example {\n}\n", classString);
    }
}
