package projectStructure.functionObjects.variable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetTypeTest {
    @Test
    void should_ReturnNameOfClass() {
        Variable variable1 = new Variable("int", 7);
        Variable variable2 = new Variable("String", "hello");

        String output1 = variable1.getType();
        String output2 = variable2.getType();

        assertEquals("Integer", output1);
        assertEquals("String", output2);
    }
}
