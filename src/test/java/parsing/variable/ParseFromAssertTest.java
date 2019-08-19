package parsing.variable;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import projectStructure.variable.Variable;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.logging.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParseFromAssertTest {
    private static Logger logger;
    private static Handler originalHandler;
    private static Level originalLevel;
    private static StreamHandler newHandler;
    private static OutputStream outputStream;

    @BeforeAll
    static void setup() {
        logger = Logger.getLogger(VariableParser.class.getName());
        originalHandler = logger.getParent().getHandlers()[0];
        originalLevel = originalHandler.getLevel();
        outputStream = new ByteArrayOutputStream();
        newHandler = new StreamHandler(outputStream, originalHandler.getFormatter());
    }

    @AfterEach
    void cleanup() {
        originalHandler.setLevel(originalLevel);
        logger.removeHandler(newHandler);
    }

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

        assertEquals("hello world", output.getObject());
        assertEquals("String", output.getType());
    }

    @Test
    void should_Log_AndReturnEmptyString_ForErrors() {
        originalHandler.setLevel(Level.OFF);
        logger.addHandler(newHandler);

        String type = "nonsense";
        String line = "assertEquals(\"hello world\", output);";

        Variable output = VariableParser.parseFromAssert(type, line);

        newHandler.flush();
        assertTrue(outputStream.toString().contains("WARNING: java.lang.ClassNotFoundException: java.lang.nonsense"));

        assertEquals("", output.getObject());
        assertEquals("String", output.getType());
    }
}
