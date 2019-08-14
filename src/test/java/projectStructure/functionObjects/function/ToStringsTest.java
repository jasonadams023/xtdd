package projectStructure.functionObjects.function;

import projectStructure.functionObjects.variable.Variable;
import projectStructure.functionObjects.signature.Signature;
import projectStructure.functionObjects.functionRequirement.FunctionRequirement;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToStringsTest {
    @Test
    void should_ReturnVoidFunction() {
        Signature signature = new Signature("Example", "void", new ArrayList<>());
        FunctionRequirement functionRequirement = FunctionRequirement.create(signature, null, null);
        Function function = Function.createFromRequirement(functionRequirement);

        List<String> functionStrings = function.toStrings();

        assertEquals("", functionStrings.get(0));
        assertEquals("static void Example() {\n", functionStrings.get(1));
        assertEquals("", functionStrings.get(2));
        assertEquals("}\n", functionStrings.get(3));
    }

    @Test
    void should_ReturnFunctionWithNullReturn_WhenReturnTypeIsSetAndReturnValueIsNot() {
        Signature signature = new Signature("Example", "String", new ArrayList<>());
        FunctionRequirement functionRequirement = FunctionRequirement.create(signature, null, null);
        Function function = Function.createFromRequirement(functionRequirement);

        List<String> functionStrings = function.toStrings();

        assertEquals("", functionStrings.get(0));
        assertEquals("static String Example() {\n", functionStrings.get(1));
        assertEquals("\treturn null;\n", functionStrings.get(2));
        assertEquals("}\n", functionStrings.get(3));
    }

    @Test
    void should_ReturnFunctionWithProperReturnValue_WhenReturnTypeAndReturnValueIsSet() {
        Signature signature = new Signature("Example", "int", new ArrayList<>());
        FunctionRequirement functionRequirement = FunctionRequirement.create(signature, null, 7);
        Function function = Function.createFromRequirement(functionRequirement);

        List<String> functionStrings = function.toStrings();

        assertEquals("", functionStrings.get(0));
        assertEquals("static int Example() {\n", functionStrings.get(1));
        assertEquals("\treturn 7;\n", functionStrings.get(2));
        assertEquals("}\n", functionStrings.get(3));
    }

    @Test
    void should_ReturnFunctionWithProperInputs() {
        List<Variable> inputs = new ArrayList<>();
        inputs.add(new Variable(1));
        inputs.add(new Variable("string"));

        List<String> inputTypes = new ArrayList<>();
        inputTypes.add(("int"));
        inputTypes.add("String");

        Signature signature = new Signature("Example", "void", inputTypes);
        FunctionRequirement functionRequirement = FunctionRequirement.create(signature, inputs, null);
        Function function = Function.createFromRequirement(functionRequirement);

        List<String> functionStrings = function.toStrings();

        assertEquals("", functionStrings.get(0));
        assertEquals("static void Example(int arg1, String arg2) {\n", functionStrings.get(1));
        assertEquals("", functionStrings.get(2));
        assertEquals("}\n", functionStrings.get(3));
    }
}
