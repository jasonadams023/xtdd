import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

//beginning of classes to generate
import example.Returns;
//end of classes to generate

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FirstTest {
    @Test
    void should_ReturnNullString() {
        String output = Returns.getNullString();
    }

    @Test
    void should_ReturnNullInt() {
        Integer output = Returns.getNullInt();
    }

    @Test
    void should_ReturnInt() {
        Integer output = Returns.getInt();

        assertEquals(7, output);
    }
}
