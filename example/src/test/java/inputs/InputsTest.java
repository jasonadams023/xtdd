import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InputsTest {
    @Test
    void should_TakeInt() {
        Integer var = 7;

        Inputs.setInt(var);
    }

    @Test
    void should_TakeArgs() {
        Integer var = 7;
        String line = "a string";

        Inputs.setArgs(var, line);
    }
}
