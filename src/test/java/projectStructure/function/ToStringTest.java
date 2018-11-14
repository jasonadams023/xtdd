package projectStructure.function;

import requirement.FunctionRequirement;
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
    void should_ReturnFunctionWithNullReturn_WhenReturnTypeIsSetAndReturnValueIsNot() {
        FunctionRequirement functionRequirement = new FunctionRequirement("Example", "String");
        Function function = Function.createFromRequirement(functionRequirement);

        String functionString = function.toString();

        assertEquals("static String Example() {\nreturn null;\n}\n", functionString);
    }

    @Test
    void should_ReturnFunctionWithProperReturnValue_WhenReturnTypeAndReturnValueIsSet() {
        FunctionRequirement functionRequirement = new FunctionRequirement("Example", "int", 7);
        Function function = Function.createFromRequirement(functionRequirement);

        String functionString = function.toString();

        assertEquals("static int Example() {\nreturn 7;\n}\n", functionString);
    }
}
