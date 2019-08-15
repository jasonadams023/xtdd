package parsing.variable;

import org.junit.jupiter.api.Test;
import projectStructure.variable.Variable;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VariableParserTest {
    @Test
    void should_ParseIntegers() {
        String line = "Integer integer = 1;";

        Variable variable = VariableParser.parseFromLine(line);

        assertEquals(1, variable.getObject());
        assertEquals("Integer", variable.getType());
    }

    @Test
    void should_ParseStrings() {
        String line = "String text = \"a string\"";

        Variable variable = VariableParser.parseFromLine(line);

        assertEquals("\"a string\"", variable.getObject());
        assertEquals("String", variable.getType());
    }

    @Test
    void should_ParseDoubles() {
        String line = "Double num = 10.2";

        Variable variable = VariableParser.parseFromLine(line);

        assertEquals(10.2, variable.getObject());
        assertEquals("Double", variable.getType());
    }
}
