package projectStructure.classObjects.javaClass;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetNameTest {
    @Test
    void should_ReturnName() {
        JavaClass javaClass = new JavaClass("Example");

        String name = javaClass.getName();

        assertEquals("Example", name);
    }
}
