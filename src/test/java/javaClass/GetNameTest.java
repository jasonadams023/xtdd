package javaClass;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetNameTest {
    @Test
    void should_ReturnName() {
        JavaClassFactory factory = new JavaClassFactory();
        JavaClass javaClass = factory.newJavaClass("Example");

        String name = javaClass.getName();

        assertEquals("Example", name);
    }
}
