package parsing.variable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParseFromAssertTest {
    @Test
    void should_ParseIntegers() {
        String type = "Integer";
        String line = "assertEquals(7, output);";
        Object output = VariableParser.parseFromAssert(type, line);

        assertEquals(7, output);
        assertEquals("Integer", output.getClass().getSimpleName());
    }

    @Test
    void should_ParseStrings() {
        String type = "String";
        String line = "assertEquals(\"hello world\", output);";
        Object output = VariableParser.parseFromAssert(type, line);

        assertEquals("\"hello world\"", output);
        assertEquals("String", output.getClass().getSimpleName());
    }
}
