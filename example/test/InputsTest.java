import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

//beginning of classes to generate
import example.Inputs;
//end of classes to generate

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FirstTest {
    @Test
    void should_TakeInt() {
        int var = 7;

        Inputs.setInt(var);
    }
}
