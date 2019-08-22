import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ReturnsTest {
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

    @Test
    void should_ReturnString() {
        String output = Returns.getString();

        assertEquals("hello world", output);
    }
}
