package function;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConstructorTest {
    @Test
    void should_ReturnNewFunctionObject() {
        Function function = new Function();

        assertEquals("", function.name);
        assertEquals("void", function.returnType);
    }
}
