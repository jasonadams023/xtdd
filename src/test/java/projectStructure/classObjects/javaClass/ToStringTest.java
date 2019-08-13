package projectStructure.classObjects.javaClass;

import projectStructure.functionObjects.signature.Signature;
import projectStructure.functionObjects.functionRequirement.FunctionRequirement;
import projectStructure.functionObjects.function.Function;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToStringTest {
    @Test
    void should_PrintEmptyClass() {
        JavaClass javaClass = new JavaClass("Class");

        String expected = "class Class {\n}\n";

        String output = javaClass.toString();

        assertEquals(expected, output);
    }

    @Test
    void should_PrintClass_WithFunctions() {
        JavaClass javaClass = new JavaClass("Class");

        Signature signature = new Signature("function", "void", new ArrayList<>());
        FunctionRequirement functionRequirement = FunctionRequirement.create(signature, null, null);
        Function function = Function.createFromRequirement(functionRequirement);
        javaClass.functions.add(function);

        String expected = "class Class {\n" +
                "\tstatic void function() {\n" +
                "\t}\n" +
                "}\n";

        String output = javaClass.toString();

        assertEquals(expected, output);
    }
}
