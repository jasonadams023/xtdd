package javaClass;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetFooterTest {
    @Test
    void should_ReturnFooter() {
        JavaClassFactory factory = new JavaClassFactory();
        JavaClass javaClass = factory.newJavaClass("Example");

        String footer = javaClass.getFooter();

        assertEquals("}\n", footer);
    }
}
