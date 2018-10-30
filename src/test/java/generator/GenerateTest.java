package generator;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GenerateTest {
    @Disabled
    @Test
    void shouldCallCreateClassForEachTestInFolder() {
        File exampleDirectory = new File("./example");
        File exampleTestDirectory = new File("./example/test");
        int expectedNumberOfCalls = exampleTestDirectory.list().length;

        Generator generatorSpy = spy(new Generator(exampleDirectory));
//        doNothing().when(generatorSpy).createClass(any());

        generatorSpy.generate();

//        verify(generatorSpy, times(expectedNumberOfCalls)).createClass(any());
    }
}
