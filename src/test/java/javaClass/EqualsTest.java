package javaClass;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EqualsTest {
    @Test
    void should_ReturnTrue_WhenNamesMatch() {
        JavaClassFactory factory = new JavaClassFactory();
        JavaClass javaClass1 = factory.newJavaClass("someName");
        JavaClass javaClass2 = factory.newJavaClass("someName");

        boolean output = javaClass1.equals(javaClass2);

        assertTrue(output);
    }

    @Test
    void should_ReturnFalse_WhenNamesDoNotMatch() {
        JavaClassFactory factory = new JavaClassFactory();
        JavaClass javaClass1 = factory.newJavaClass("someName");
        JavaClass javaClass2 = factory.newJavaClass("someOtherName");

        boolean output = javaClass1.equals(javaClass2);

        assertFalse(output);
    }

    @Test
    void should_ReturnFalse_WhenDifferentObjects() {
        JavaClassFactory factory = new JavaClassFactory();
        JavaClass javaClass1 = factory.newJavaClass("someName");

        boolean output = javaClass1.equals("Not a JavaClass");

        assertFalse(output);
    }
}
