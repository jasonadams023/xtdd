import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

//beginning of classes to generate
import example.First;
//end of classes to generate

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FirstTest {
    @Test
    void shouldExample() {
        First.example();
    }

    @Test
    void shouldDifferent() {
        First.different();
    }
}
