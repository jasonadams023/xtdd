package generator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GetClassNameTest {
    File exampleDirectory = new File("./example");

    @Test
    void shouldReturnClassName() {
        String className = "Example";
        File testFile = new File("./example/test/" + className + "Test.java");
        Generator generator = new Generator(exampleDirectory);

        String name = generator.getClassName(testFile);

        assertEquals(className, name);
    }

    @Test
    void shouldReturnDifferentClassName() {
        String className = "Different";
        File testFile = new File("./example/test/" + className + "Test.java");
        Generator generator = new Generator(exampleDirectory);

        String name = generator.getClassName(testFile);

        assertEquals(className, name);
    }
}
