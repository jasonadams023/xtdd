package javaClass;

import function.Function;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FactoryTest {
    @Test
    void should_CreateNewJavaClass_WithName() {
        JavaClass javaClass = JavaClassFactory.newJavaClass("Example");

        assertEquals("Example", javaClass.name);
        assertEquals(new ArrayList<Function>(), javaClass.functions);
    }
}
