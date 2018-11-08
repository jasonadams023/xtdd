package function;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FunctionFactoryTest {
    @Test
    void should_CreateNewFunction_WithName() {
        FunctionFactory functionFactory = new FunctionFactory();

        Function output = functionFactory.newFunction("functionName");

        String expectedString = "static void functionName() {\n}\n";

        assertEquals(expectedString, output.toString());
    }
}
