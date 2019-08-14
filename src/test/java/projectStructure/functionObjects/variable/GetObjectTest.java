package projectStructure.functionObjects.variable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetObjectTest {
    @Test
    void should_ReturnUnderlyingObject() {
        Variable variable1 = new Variable("int", 7);
        Variable variable2 = new Variable("String", "hello");

        Object output1 = variable1.getObject();
        Object output2 = variable2.getObject();

        assertEquals(7, output1);
        assertEquals("hello", output2);
    }
}
