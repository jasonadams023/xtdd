package parsing.variable;

import org.junit.jupiter.api.Test;
import projectStructure.variable.Variable;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParseFromAssertTest {
    @Test
    void should_ParseIntegers() {
        String type = "Integer";
        String line = "assertEquals(7, output);";
        Variable output = VariableParser.parseFromAssert(type, line);

        assertEquals(7, output.getObject());
        assertEquals("Integer", output.getType());
    }

    @Test
    void should_ParseStrings() {
        String type = "String";
        String line = "assertEquals(\"hello world\", output);";
        Variable output = VariableParser.parseFromAssert(type, line);

        assertEquals("\"hello world\"", output.getObject());
        assertEquals("String", output.getType());
    }
}
