package projectStructure.functionObjects.inputOutput;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectStructure.variable.Variable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EqualsTest {
    private InputOutput inputOutput;

    @BeforeEach
    void setup() {
        List<Variable> inputs = new ArrayList<>();
        inputs.add(Variable.create(1));
        Object returnValue = 1;
        inputOutput = new InputOutput(inputs, Variable.create(returnValue));
    }

    @Test
    void should_ReturnFalseForNull() {
        boolean output = inputOutput.equals(null);

        assertFalse(output);
    }

    @Test
    void should_ReturnFalseForDifferentObject() {
        boolean output = inputOutput.equals(1);

        assertFalse(output);
    }

    @Test
    void should_ReturnFalseForDifferentInputs() {
        List<Variable> inputs = new ArrayList<>();
        inputs.add(Variable.create(1));
        inputs.add(Variable.create(1));
        Object returnValue = 1;
        InputOutput differentInputOutput = new InputOutput(inputs, Variable.create(returnValue));

        boolean output = inputOutput.equals(differentInputOutput);

        assertFalse(output);
    }

    @Test
    void should_ReturnFalseForDifferentReturnValues() {
        List<Variable> inputs = new ArrayList<>();
        inputs.add(Variable.create(1));
        Object returnValue = 2;
        InputOutput differentInputOutput = new InputOutput(inputs, Variable.create(returnValue));

        boolean output = inputOutput.equals(differentInputOutput);

        assertFalse(output);
    }

    @Test
    void should_ReturnTrueForSameValues() {
        List<Variable> inputs = new ArrayList<>();
        inputs.add(Variable.create(1));
        Object returnValue = 1;
        InputOutput matchingInputOutput = new InputOutput(inputs, Variable.create(returnValue));

        boolean output = inputOutput.equals(matchingInputOutput);

        assertTrue(output);
    }
}
