package projectStructure.classObjects.javaClass;

import projectStructure.functionObjects.signature.Signature;
import projectStructure.functionObjects.functionRequirement.FunctionRequirement;
import org.junit.jupiter.api.Test;
import projectStructure.variable.Variable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddRequirementTest {
    @Test
    void should_AddFunction_BasedOnRequirement() {
        Signature signature = new Signature("function", "void", null);
        FunctionRequirement functionRequirement = FunctionRequirement.create(signature, null, null);
        JavaClass javaClass = new JavaClass("Example");

        javaClass.addRequirement(functionRequirement);

        assertEquals(1, javaClass.functions.size());
    }

    @Test
    void should_NotAddFunction_WhenItAlreadyExists() {
        Signature signature = new Signature("function", "void", null);
        FunctionRequirement functionRequirement = FunctionRequirement.create(signature, null, null);
        JavaClass javaClass = new JavaClass("Example");

        javaClass.addRequirement(functionRequirement);
        javaClass.addRequirement(functionRequirement);

        assertEquals(1, javaClass.functions.size());
    }

    @Test
    void should_AddNewRequirement_ToFunction() {
        List<String> inputTypes = new ArrayList<>();
        inputTypes.add("Integer");
        Signature signature = new Signature("function", "Integer", inputTypes);
        List<Variable> inputs = new ArrayList<>();
        inputs.add(Variable.create(1));
        Variable returnValue = Variable.create(1);
        FunctionRequirement functionRequirement = FunctionRequirement.create(signature, inputs, returnValue);

        List<Variable> inputs2 = new ArrayList<>();
        inputs2.add(Variable.create(1));
        Variable returnValue2 = Variable.create(1);
        FunctionRequirement functionRequirement2 = FunctionRequirement.create(signature, inputs2, returnValue2);

        JavaClass javaClass = new JavaClass("Example");

        javaClass.addRequirement(functionRequirement);
        javaClass.addRequirement(functionRequirement2);

        assertEquals(1, javaClass.functions.size());
        assertEquals(2, javaClass.functions.get(0).inputOutputs.size());
    }
}
