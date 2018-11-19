import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

//beginning of classes to generate
import example.InputOutput;
//end of classes to generate

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InputOutputTest {
    @Test
    void should_Add7And3() {
        int first = 7;
        int second = 3;

        int output = InputOutput.add(first, second);

        assertEquals(10, output);
    }

    @Test
    void should_Add7And4() {
        int first = 7;
        int second = 4;

        int output = InputOutput.add(first, second);

        assertEquals(11, output);
    }
}
