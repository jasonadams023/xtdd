package javaClass;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetNameTest {
    @Test
    void should_ReturnName() {
        JavaClass javaClass = new JavaClass("Example", null);

        String name = javaClass.getName();

        assertEquals("Example", name);
    }
}
