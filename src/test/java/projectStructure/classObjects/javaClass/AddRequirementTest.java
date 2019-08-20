package projectStructure.classObjects.javaClass;

import projectStructure.functionObjects.signature.Signature;
import projectStructure.functionObjects.functionRequirement.FunctionRequirement;
import org.junit.jupiter.api.Test;
import projectStructure.variable.Variable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddRequirementTest {
    private static Variable intOneVariable = Variable.create(1);
    private static Variable intTwoVariable = Variable.create(2);
    private static Variable[] intOneList = {intOneVariable};
    private static Variable[] intTwoList = {intTwoVariable};
    private static List<Variable> intOneInputs = Arrays.asList(intOneList);
    private static List<Variable> intTwoInputs = Arrays.asList(intTwoList);
    private static List<String> singleIntegerInputs = Collections.singletonList("Integer");
    private static Signature voidNullSignature = new Signature("function", "void", null);
    private static Signature intIntSignature = new Signature("function", "Integer", singleIntegerInputs);
    private static FunctionRequirement nullRequirement = FunctionRequirement.create(voidNullSignature, null, null);

    @Test
    void should_AddFunction_BasedOnRequirement() {
        JavaClass javaClass = new JavaClass("Example");

        javaClass.addRequirement(nullRequirement);

        assertEquals(1, javaClass.functions.size());
    }

    @Test
    void should_NotAddFunction_WhenItAlreadyExists() {
        JavaClass javaClass = new JavaClass("Example");

        javaClass.addRequirement(nullRequirement);
        javaClass.addRequirement(nullRequirement);

        assertEquals(1, javaClass.functions.size());
    }

    @Test
    void should_AddNewRequirement_ToFunction3() {
        FunctionRequirement functionRequirement = FunctionRequirement.create(intIntSignature, intOneInputs, intOneVariable);
        FunctionRequirement functionRequirement2 = FunctionRequirement.create(intIntSignature, intTwoInputs, intTwoVariable);

        JavaClass javaClass = new JavaClass("Example");

        javaClass.addRequirement(functionRequirement);
        javaClass.addRequirement(functionRequirement2);

        assertEquals(1, javaClass.functions.size());
        assertEquals(2, javaClass.functions.get(0).inputOutputs.size());
    }
}
