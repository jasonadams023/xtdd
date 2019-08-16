package projectStructure.variable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EqualsTest {
    @Test
    void should_ReturnFalseForNull() {
        Variable variable = Variable.create(1);

        boolean output = variable.equals(null);

        assertFalse(output);
    }

    @Test
    void should_ReturnFalseForDifferentObject() {
        Variable variable = Variable.create(1);

        boolean output = variable.equals(1);

        assertFalse(output);
    }

    @Test
    void should_ReturnFalseForDifferentValue() {
        Variable variable = Variable.create(1);
        Variable differentVariable = Variable.create(2);

        boolean output = variable.equals(differentVariable);

        assertFalse(output);
    }

    @Test
    void should_ReturnTrueForSameValues() {
        Variable variable = Variable.create(1);
        Variable matchingVariable = Variable.create(1);

        boolean output = variable.equals(matchingVariable);

        assertTrue(output);
    }
}
