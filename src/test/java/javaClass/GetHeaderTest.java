package javaClass;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetHeaderTest {
    @Test
    void should_ReturnHeader() {
        JavaClassFactory factory = new JavaClassFactory();
        JavaClass javaClass = factory.newJavaClass("Example");

        String header = javaClass.getHeader();

        assertEquals("class Example {\n", header);
    }

    @Test
    void should_ReturnHeaderWithDifferentName() {
        JavaClassFactory factory = new JavaClassFactory();
        JavaClass javaClass = factory.newJavaClass("Different");

        String header = javaClass.getHeader();

        assertEquals("class Different {\n", header);
    }
}
