package generator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GetClassNameTest {
    @Test
    void shouldReturnClassName() {
        String className = "Example";
        File testFile = new File("./example/test/" + className + "Test.java");

        String name = Generator.getClassName(testFile);

        assertEquals(className, name);
    }

    @Test
    void shouldReturnDifferentClassName() {
        String className = "Different";
        File testFile = new File("./example/test/" + className + "Test.java");

        String name = Generator.getClassName(testFile);

        assertEquals(className, name);
    }
}
