package fileManager;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WriteFileTest {
    private static Logger logger;
    private static Handler originalHandler;
    private static Level originalLevel;
    private static StreamHandler newHandler;
    private static OutputStream outputStream;
    private static FilesWrapper filesWrapper;
    private static FileManager fileManager;
    private static Path path;
    private static String text;

    @BeforeAll
    static void setup() {
        logger = Logger.getLogger(FileManager.class.getName());
        originalHandler = logger.getParent().getHandlers()[0];
        originalLevel = originalHandler.getLevel();
        outputStream = new ByteArrayOutputStream();
        newHandler = new StreamHandler(outputStream, originalHandler.getFormatter());

        filesWrapper = mock(FilesWrapper.class);
        fileManager = new FileManager(filesWrapper);
        path = Paths.get("./ExamplePath.txt");
        text = "Example text";
    }

    @AfterEach
    void cleanup() {
        originalHandler.setLevel(originalLevel);
        logger.removeHandler(newHandler);
    }

    @Test
    void should_CallWriteMethodWithArgs() throws IOException {
        fileManager.writeFile(path, text);

        verify(filesWrapper, times(1)).write(path, text.getBytes());
    }

    @Test
    void should_LogWarning_WhenExceptionThrown() throws IOException {
        originalHandler.setLevel(Level.OFF);
        logger.addHandler(newHandler);

        IOException exception = new IOException();
        when(filesWrapper.write(any(), any())).thenThrow(exception);

        fileManager.writeFile(path, text);

        newHandler.flush();
        assertThat(outputStream.toString(), containsString("WARNING: Failed to write file: ./ExamplePath.txt"));
        assertThat(outputStream.toString(), containsString("Error message: " + exception));
    }
}
