package TestParser;

import Requirements.Requirements;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class ParseTestFileTest {
    @Test
    void should_ReturnEmptyList_WhenNoRequirementsToReturn() {
        Path path = mock(Path.class);

        List<Requirements> output = TestParser.parseTestFile(path);

        assertEquals(new ArrayList<Requirements>(), output);
    }
}
