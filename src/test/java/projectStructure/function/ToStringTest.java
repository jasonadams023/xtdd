package projectStructure.function;

import projectStructure.Variable.Variable;
import projectStructure.function.signature.Signature;
import requirement.FunctionRequirement;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToStringTest {
    @Test
    void should_ReturnEmptyString_WhenNameNotSet() {
        Signature signature = new Signature("", "void", null);
        FunctionRequirement functionRequirement = FunctionRequirement.create(signature, null, null);
        Function function = Function.createFromRequirement(functionRequirement);

        String functionString = function.toString();

        assertEquals("", functionString);
    }

    @Test
    void should_ReturnVoidFunction() {
        Signature signature = new Signature("Example", "void", new ArrayList<>());
        FunctionRequirement functionRequirement = FunctionRequirement.create(signature, null, null);
        Function function = Function.createFromRequirement(functionRequirement);

        String functionString = function.toString();

        assertEquals("static void Example() {\n}\n", functionString);
    }

    @Test
    void should_ReturnFunctionWithNullReturn_WhenReturnTypeIsSetAndReturnValueIsNot() {
        Signature signature = new Signature("Example", "String", new ArrayList<>());
        FunctionRequirement functionRequirement = FunctionRequirement.create(signature, null, null);
        Function function = Function.createFromRequirement(functionRequirement);

        String functionString = function.toString();

        assertEquals("static String Example() {\nreturn null;\n}\n", functionString);
    }

    @Test
    void should_ReturnFunctionWithProperReturnValue_WhenReturnTypeAndReturnValueIsSet() {
        Signature signature = new Signature("Example", "int", new ArrayList<>());
        FunctionRequirement functionRequirement = FunctionRequirement.create(signature, null, 7);
        Function function = Function.createFromRequirement(functionRequirement);

        String functionString = function.toString();

        assertEquals("static int Example() {\nreturn 7;\n}\n", functionString);
    }

    @Test
    void should_ReturnFunctionWithProperInputs() {
        List<Variable> inputs = new ArrayList<>();
        inputs.add(new Variable("int"));
        inputs.add(new Variable("String"));

        List<String> inputTypes = new ArrayList<>();
        inputTypes.add(("int"));
        inputTypes.add("String");

        Signature signature = new Signature("Example", "void", inputTypes);
        FunctionRequirement functionRequirement = FunctionRequirement.create(signature, inputs, null);
        Function function = Function.createFromRequirement(functionRequirement);

        String functionString = function.toString();

        assertEquals("static void Example(int arg1, String arg2) {\n}\n", functionString);
    }
}
