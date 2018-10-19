package function;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConstructorTest {
    @Test
    void should_ReturnNewFunctionObject() {
        Function function = new Function();

        assertNotNull(function);
    }
}
