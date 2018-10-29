package javaClass;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetFooterTest {
    @Test
    void should_ReturnFooter() {
        File file = new File("./ExampleTest.java");
        JavaClass javaClass = new JavaClass(file);

        String footer = javaClass.getFooter();

        assertEquals("}\n", footer);
    }
}
