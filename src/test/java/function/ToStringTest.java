package function;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToStringTest {
    @Test
    void should_ReturnEmptyString_WhenNameNotSet() {
        Function function = new Function("");

        String functionString = function.toString();

        assertEquals("", functionString);
    }

    @Test
    void should_ReturnVoidFunction_WhenReturnIsNotSet() {
        Function function = new Function("Example");

        String functionString = function.toString();

        assertEquals("static void Example() {\n}\n", functionString);
    }

    @Test
    void should_ReturnFunctionWithProperReturn_WhenReturnIsSet() {
        Function function = new Function("Example");
        function.setReturnType("String");

        String functionString = function.toString();

        assertEquals("static String Example() {\nreturn null;\n}\n", functionString);
    }
}
