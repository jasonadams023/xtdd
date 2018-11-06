package javaClass;

import function.Function;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.*;

class ToStringTest {
    @Test
    void should_CallHeaderAndFooter() {
        JavaClassFactory factory = new JavaClassFactory();
        JavaClass javaClass = factory.newJavaClass("Example");
        JavaClass javaClassSpy = spy(javaClass);

        willReturn("header ").given(javaClassSpy).getHeader();
        willReturn("footer ").given(javaClassSpy).getFooter();

        String output = javaClassSpy.toString();

        verify(javaClassSpy, times(1)).getHeader();
        verify(javaClassSpy, times(1)).getFooter();

        assertEquals("header footer ", output);
    }

    @Test
    void should_IncludeFunctions() {
        JavaClassFactory factory = new JavaClassFactory();
        JavaClass javaClass = factory.newJavaClass("Example");
        JavaClass javaClassSpy = spy(javaClass);
        Function functionMock = mock(Function.class);

        List<Function> functions = new ArrayList<>();
        functions.add(functionMock);
        javaClassSpy.functions = functions;

        willReturn("header ").given(javaClassSpy).getHeader();
        willReturn("function ").given(functionMock).toString();
        willReturn("footer ").given(javaClassSpy).getFooter();

        String output = javaClassSpy.toString();

        assertEquals("header function footer ", output);
    }
}
