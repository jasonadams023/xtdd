package component;

import org.junit.jupiter.api.Test;
import function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FunctionTest {
    @Test
    void should_ReturnFunctionAsString() {
        Function function = new Function();

        String functionString = function.toString();

        String expectedString = "";

        assertEquals(expectedString, functionString);
    }
}
