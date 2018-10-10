import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

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
