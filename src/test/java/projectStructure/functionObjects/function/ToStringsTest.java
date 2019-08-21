package projectStructure.functionObjects.function;

import projectStructure.functionObjects.inputOutput.InputOutput;
import projectStructure.variable.Variable;
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

        assertEquals("static void Example() {\n", functionStrings.get(0));
        assertEquals("}\n", functionStrings.get(1));
    }

    @Test
    void should_ReturnFunctionWithNullReturn_WhenReturnTypeIsSetAndReturnValueIsNot() {
        Signature signature = new Signature("Example", "String", new ArrayList<>());
        FunctionRequirement functionRequirement = FunctionRequirement.create(signature, null, Variable.create(null));
        Function function = Function.createFromRequirement(functionRequirement);

        List<String> functionStrings = function.toStrings();

        assertEquals("static String Example() {\n", functionStrings.get(0));
        assertEquals("\treturn null;\n", functionStrings.get(1));
        assertEquals("}\n", functionStrings.get(2));
    }

    @Test
    void should_ReturnFunctionWithProperReturnValue_WhenReturnTypeAndReturnValueIsSet() {
        Signature signature = new Signature("Example", "int", new ArrayList<>());
        FunctionRequirement functionRequirement = FunctionRequirement.create(signature, null, Variable.create(7));
        Function function = Function.createFromRequirement(functionRequirement);

        List<String> functionStrings = function.toStrings();

        assertEquals("static int Example() {\n", functionStrings.get(0));
        assertEquals("\treturn 7;\n", functionStrings.get(1));
        assertEquals("}\n", functionStrings.get(2));
    }

    @Test
    void should_ReturnFunctionWithProperInputs() {
        List<Variable> inputs = new ArrayList<>();
        inputs.add(Variable.create(1));
        inputs.add(Variable.create("string"));

        List<String> inputTypes = new ArrayList<>();
        inputTypes.add(("int"));
        inputTypes.add("String");

        Signature signature = new Signature("Example", "void", inputTypes);
        FunctionRequirement functionRequirement = FunctionRequirement.create(signature, inputs, null);
        Function function = Function.createFromRequirement(functionRequirement);

        List<String> functionStrings = function.toStrings();

        assertEquals("static void Example(int arg1, String arg2) {\n", functionStrings.get(0));
        assertEquals("}\n", functionStrings.get(1));
    }

    @Test
    void should_ReturnIfElse_ForMultipleReturns() {
        List<String> inputTypes = new ArrayList<>();
        inputTypes.add(("int"));

        Signature signature = new Signature("Example", "Integer", inputTypes);

        List<Variable> inputs = new ArrayList<>();
        inputs.add(Variable.create(1));
        Variable returnValue = Variable.create(1);
        FunctionRequirement functionRequirement = FunctionRequirement.create(signature, inputs, returnValue);

        Function function = Function.createFromRequirement(functionRequirement);

        List<Variable> inputs2 = new ArrayList<>();
        inputs2.add(Variable.create(2));
        Variable returnValue2 = Variable.create(2);
        InputOutput inputOutput = new InputOutput(inputs2, returnValue2);

        function.addInputOutput(inputOutput);

        List<String> functionStrings = function.toStrings();

        assertEquals("static Integer Example(int arg1) {\n", functionStrings.get(0));
        assertEquals("\tif (arg1 == 1) {\n", functionStrings.get(1));
        assertEquals("\t\treturn 1;\n", functionStrings.get(2));
        assertEquals("\t} else if (arg1 == 2) {\n", functionStrings.get(3));
        assertEquals("\t\treturn 2;\n", functionStrings.get(4));
        assertEquals("\t} else {\n", functionStrings.get(5));
        assertEquals("\t\treturn 1;\n", functionStrings.get(6));
        assertEquals("\t}\n", functionStrings.get(7));
        assertEquals("}\n", functionStrings.get(8));
    }
}
