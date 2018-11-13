package function;

import Requirement.FunctionRequirement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToStringTest {
    @Test
    void should_ReturnEmptyString_WhenNameNotSet() {
        FunctionRequirement functionRequirement = new FunctionRequirement("", "void");
        Function function = Function.createFromRequirement(functionRequirement);

        String functionString = function.toString();

        assertEquals("", functionString);
    }

    @Test
    void should_ReturnFunctionWithProperReturn_WhenReturnIsSet() {
        FunctionRequirement functionRequirement = new FunctionRequirement("Example", "String");
        Function function = Function.createFromRequirement(functionRequirement);

        String functionString = function.toString();

        assertEquals("static String Example() {\nreturn null;\n}\n", functionString);
    }
}
