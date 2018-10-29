package function;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EqualsTest {
    @Test
    void should_ReturnTrue_WhenNamesMatch () {
        Function function1 = new Function();
        function1.setName("someName");
        Function function2 = new Function();
        function2.setName("someName");

        boolean output = function1.equals(function2);

        assertTrue(output);
    }

    @Test
    void should_ReturnFalse_WhenNamesDoNotMatch () {
        Function function1 = new Function();
        function1.setName("someName");
        Function function2 = new Function();
        function2.setName("someOtherName");

        boolean output = function1.equals(function2);

        assertFalse(output);
    }

    @Test
    void should_ReturnFalse_WhenDifferentObjects () {
        Function function1 = new Function();
        function1.setName("someName");

        boolean output = function1.equals("Not a function");

        assertFalse(output);
    }
}
