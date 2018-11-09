package javaClass;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EqualsTest {
    @Test
    void should_ReturnTrue_WhenNamesMatch() {
        JavaClass javaClass1 = new JavaClass("someName", null);
        JavaClass javaClass2 = new JavaClass("someName", null);

        boolean output = javaClass1.equals(javaClass2);

        assertTrue(output);
    }

    @Test
    void should_ReturnFalse_WhenNamesDoNotMatch() {
        JavaClass javaClass1 = new JavaClass("someName", null);
        JavaClass javaClass2 = new JavaClass("someOtherName", null);

        boolean output = javaClass1.equals(javaClass2);

        assertFalse(output);
    }

    @Test
    void should_ReturnFalse_WhenDifferentObjects() {
        JavaClass javaClass1 = new JavaClass("someName", null);

        boolean output = javaClass1.equals("Not a JavaClass");

        assertFalse(output);
    }
}
