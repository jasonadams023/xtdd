package projectStructure.variable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateTest {
    @Test
    void should_ReturnVariableWithInteger() {
        Variable variable = Variable.create(7);

        assertEquals(7, variable.getObject());
    }

    @Test
    void should_ReturnVariableWithEnrichedString() {
        Variable variable = Variable.create("hello world");

        assertEquals("\"hello world\"", variable.getObject());
    }
}
