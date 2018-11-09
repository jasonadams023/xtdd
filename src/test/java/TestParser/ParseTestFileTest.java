package TestParser;

import Requirement.Requirement;
import fileManager.FileManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;

class ParseTestFileTest {
    private Path path;
    private TestParser parser;
    private FileManager fileManager;

    @BeforeEach
    void setup() {
        path = mock(Path.class);
        fileManager = mock(FileManager.class);
        parser = new TestParser(fileManager);
    }

    @Test
    void should_ReturnEmptyList_WhenNoRequirementsToReturn() {
        List<Requirement> output = parser.parseTestFile(path);

        assertEquals(new ArrayList<Requirement>(), output);
    }

    @Test
    void should_ReturnRequirement_FromTestFile() {
        List<String> lines = new ArrayList<>();
        lines.add(TestParser.startFlag);
        lines.add("import example.Example;");
        lines.add(TestParser.endFlag);
        willReturn(lines).given(fileManager).readAllLines(path);

        List<Requirement> output = parser.parseTestFile(path);

        assertEquals(1, output.size());
        assertEquals("Example", output.get(0).className);
    }

    @Test
    void should_NotReturnRequirements_ForNonGeneratedClasses() {
        List<String> lines = new ArrayList<>();
        lines.add("import example.Example;");
        willReturn(lines).given(fileManager).readAllLines(path);

        List<Requirement> output = parser.parseTestFile(path);

        assertEquals(0, output.size());
    }
}
