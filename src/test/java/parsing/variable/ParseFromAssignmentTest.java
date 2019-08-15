package parsing.variable;

import org.junit.jupiter.api.Test;
import projectStructure.variable.Variable;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParseFromAssignmentTest {
    @Test
    void should_ParseIntegers() {
        String line = "Integer integer = 1;";

        Variable variable = VariableParser.parseFromAssignment(line);

        assertEquals(1, variable.getObject());
    }

    @Test
    void should_ParseStrings() {
        String line = "String text = \"a string\"";

        Variable variable = VariableParser.parseFromAssignment(line);

        assertEquals("a string", variable.getObject());
    }

    @Test
    void should_ParseDoubles() {
        String line = "Double num = 10.2";

        Variable variable = VariableParser.parseFromAssignment(line);

        assertEquals(10.2, variable.getObject());
    }
}
