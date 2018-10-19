package function;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToStringTest {
    @Test
    void should_ReturnEmptyString_WhenNameNotSet() {
        Function function = new Function();

        String functionString = function.toString();

        assertEquals("", functionString);
    }

    @Test
    void should_ReturnVoidFunction_WhenReturnIsNotSet() {
        Function function = new Function();
        function.setName("Example");

        String functionString = function.toString();

        assertEquals("void Example() {\n}\n", functionString);
    }

    @Test
    void should_ReturnFunctionWithProperReturn_WhenReturnIsSet() {
        Function function = new Function();
        function.setName("Example");
        function.setReturnType("String");

        String functionString = function.toString();

        assertEquals("String Example() {\n}\n", functionString);
    }
}
