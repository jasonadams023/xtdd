package function;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SetReturnValueTest {
    @Test
    void should_SetReturn() {
        Function function = new Function("Name");

        function.setReturnType("String");

        assertEquals("String", function.returnType);
    }

    @Test
    void should_SetDifferentReturn_WhenGivenDifferentInput() {
        Function function = new Function("Name");

        function.setReturnType("int");

        assertEquals("int", function.returnType);
    }
}
