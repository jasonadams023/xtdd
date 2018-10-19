package function;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SetNameTest {
    @Test
    void should_SetName() {
        Function function = new Function();

        function.setName("ExampleName");

        assertEquals("ExampleName", function.name);
    }
}
