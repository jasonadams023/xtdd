package projectStructure.classObjects.javaClass;

import projectStructure.functionObjects.signature.Signature;
import projectStructure.functionObjects.functionRequirement.FunctionRequirement;
import projectStructure.functionObjects.function.Function;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToStringTest {
    private JavaClass javaClass = new JavaClass("Class");

    @Test
    void should_PrintEmptyClass() {
        String expected = "class Class {\n}\n";

        String output = javaClass.toString();

        assertEquals(expected, output);
    }

    @Test
    void should_PrintClass_WithFunctions() {
        addFunctionToClass("function");

        String expected = "class Class {\n" +
                "\tstatic void function() {\n" +
                "\t}\n" +
                "}\n";

        String output = javaClass.toString();

        assertEquals(expected, output);
    }

    @Test
    void should_PrintClass_WithSpaceBetweenFunctions() {
        addFunctionToClass("function");
        addFunctionToClass("other");

        String expected = "class Class {\n" +
                "\tstatic void function() {\n" +
                "\t}\n" +
                "\n" +
                "\tstatic void other() {\n" +
                "\t}\n" +
                "}\n";

        String output = javaClass.toString();

        assertEquals(expected, output);
    }

    private void addFunctionToClass(String name) {
        Signature functionSignature = new Signature(name, "void", new ArrayList<>());
        FunctionRequirement functionRequirement = FunctionRequirement.create(functionSignature, null, null);
        Function function = Function.createFromRequirement(functionRequirement);
        javaClass.functions.add(function);
    }
}
