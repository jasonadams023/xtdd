package javaClass;

import function.Function;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FactoryTest {
    @Test
    void should_CreateNewJavaClass_WithName() {
        JavaClassFactory javaClassFactory = new JavaClassFactory();

        JavaClass javaClass = javaClassFactory.newJavaClass("Example");

        assertEquals("Example", javaClass.name);
        assertEquals(new ArrayList<Function>(), javaClass.functions);
        assertNotNull(javaClass.fileManager);
    }
}
