import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

//beginning of classes to generate
import example.Returns;
//end of classes to generate

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FirstTest {
    @Test
    void should_ReturnString() {
        String output = Returns.getString();
    }
}
