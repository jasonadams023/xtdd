package projectStructure.function;

import projectStructure.Variable.Variable;
import requirement.FunctionRequirement;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
    void should_ReturnVoidFunction() {
        FunctionRequirement functionRequirement = new FunctionRequirement("Example", "void");
        Function function = Function.createFromRequirement(functionRequirement);

        String functionString = function.toString();

        assertEquals("static void Example() {\n}\n", functionString);
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

    @Test
    void should_ReturnFunctionWithProperInputs() {
        List<Variable> inputs = new ArrayList<>();
        inputs.add(new Variable("int", 7));
        inputs.add(new Variable("String", "a string"));
        FunctionRequirement functionRequirement = new FunctionRequirement("Example", "void", inputs, null);
        Function function = Function.createFromRequirement(functionRequirement);

        String functionString = function.toString();

        assertEquals("static void Example(int arg1, String arg2) {\n}\n", functionString);
    }
}
