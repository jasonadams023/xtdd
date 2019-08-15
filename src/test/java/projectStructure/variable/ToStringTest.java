package projectStructure.variable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToStringTest {
    @Test
    void should_ReturnUnderlyingIntegerAsString() {
        Variable variable = Variable.create(1);

        String output = variable.toString();

        assertEquals("1", output);
    }

    @Test
    void should_ReturnUnderlyingStringWithQuotes() {
        Variable variable = Variable.create("hello world");

        String output = variable.toString();

        assertEquals("\"hello world\"", output);
    }
}
