import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CalculatorTest {
    @Test
    void should_Add_OneToOne() {
        Integer first = 1;
        Integer second = 1;

        Integer output = Calculator.add(first, second);

        assertEquals(2, output);
    }

    @Test
    void should_Add_TwoToOne() {
        Integer first = 1;
        Integer second = 2;

        Integer output = Calculator.add(first, second);

        assertEquals(3, output);
    }

    @Test
    void should_Add_ThreeToOne() {
        Integer first = 1;
        Integer second = 3;

        Integer output = Calculator.add(first, second);

        assertEquals(4, output);
    }

    @Test
    void should_Add_FourToOne() {
        Integer first = 1;
        Integer second = 4;

        Integer output = Calculator.add(first, second);

        assertEquals(5, output);
    }

    @Test
    void should_Add_FiveToOne() {
        Integer first = 1;
        Integer second = 5;

        Integer output = Calculator.add(first, second);

        assertEquals(6, output);
    }

    @Test
    void should_Add_SixToOne() {
        Integer first = 1;
        Integer second = 6;

        Integer output = Calculator.add(first, second);

        assertEquals(7, output);
    }

    @Test
    void should_Add_SevenToOne() {
        Integer first = 1;
        Integer second = 7;

        Integer output = Calculator.add(first, second);

        assertEquals(8, output);
    }

    @Test
    void should_Add_EightToOne() {
        Integer first = 1;
        Integer second = 8;

        Integer output = Calculator.add(first, second);

        assertEquals(9, output);
    }

    @Test
    void should_Add_NineToOne() {
        Integer first = 1;
        Integer second = 9;

        Integer output = Calculator.add(first, second);

        assertEquals(10, output);
    }

    @Test
    void should_Add_OneToTwo() {
        Integer first = 2;
        Integer second = 1;

        Integer output = Calculator.add(first, second);

        assertEquals(3, output);
    }

    @Test
    void should_Add_OneToThree() {
        Integer first = 3;
        Integer second = 1;

        Integer output = Calculator.add(first, second);

        assertEquals(4, output);
    }

    @Test
    void should_Add_OneToFour() {
        Integer first = 4;
        Integer second = 1;

        Integer output = Calculator.add(first, second);

        assertEquals(5, output);
    }

    @Test
    void should_Add_OneToFive() {
        Integer first = 5;
        Integer second = 1;

        Integer output = Calculator.add(first, second);

        assertEquals(6, output);
    }

    @Test
    void should_Add_OneToSix() {
        Integer first = 6;
        Integer second = 1;

        Integer output = Calculator.add(first, second);

        assertEquals(7, output);
    }

    @Test
    void should_Add_OneToSeven() {
        Integer first = 7;
        Integer second = 1;

        Integer output = Calculator.add(first, second);

        assertEquals(8, output);
    }

    @Test
    void should_Add_OneToEight() {
        Integer first = 8;
        Integer second = 1;

        Integer output = Calculator.add(first, second);

        assertEquals(9, output);
    }

    @Test
    void should_Add_OneToNine() {
        Integer first = 9;
        Integer second = 1;

        Integer output = Calculator.add(first, second);

        assertEquals(10, output);
    }
}