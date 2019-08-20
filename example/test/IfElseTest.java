import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

//beginning of classes to generate
import example.IfElse;
//end of classes to generate

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IfElseTest {
    @Test
    void should_Take7_AndReturnHello() {
        Integer input = 7;

        String output = IfElse.doThing(input);

        assertEquals("hello", output);
    }

    @Test
    void should_Take3_AndReturnBye() {
        Integer input = 3;

        String output = IfElse.doThing(input);

        assertEquals("bye", output);
    }
}
