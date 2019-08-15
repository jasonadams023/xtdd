package projectStructure.variable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetTypeTest {
    @Test
    void should_ReturnNameOfClass() {
        Variable variable1 = Variable.create(1);
        Variable variable2 = Variable.create("string");

        String output1 = variable1.getType();
        String output2 = variable2.getType();

        assertEquals("Integer", output1);
        assertEquals("String", output2);
    }
}
