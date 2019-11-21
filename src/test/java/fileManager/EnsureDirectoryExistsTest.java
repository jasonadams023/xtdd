package fileManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
import static org.mockito.Mockito.*;

class EnsureDirectoryExistsTest {
    private static Logger logger;
    private static Handler originalHandler;
    private static Level originalLevel;
    private static StreamHandler newHandler;
    private static OutputStream outputStream;

    @BeforeAll
    static void setup() {
        logger = Logger.getLogger(FileManager.class.getName());
        originalHandler = logger.getParent().getHandlers()[0];
        originalLevel = originalHandler.getLevel();
        outputStream = new ByteArrayOutputStream();
        newHandler = new StreamHandler(outputStream, originalHandler.getFormatter());
    }

    @AfterEach
    void cleanup() {
        originalHandler.setLevel(originalLevel);
        logger.removeHandler(newHandler);
    }

    @Test
    void should_CreateDirectories() throws IOException {
        FilesWrapper filesWrapper = mock(FilesWrapper.class);
        FileManager fileManager = new FileManager(filesWrapper);
        Path path = Paths.get("./file");

        fileManager.ensureDirectoryExists(path);

        verify(filesWrapper).createDirectories(path);
    }

    @Test
    void should_LogWarning_WhenExceptionThrown() throws IOException {
        originalHandler.setLevel(Level.OFF);
        logger.addHandler(newHandler);

        FilesWrapper filesWrapper = mock(FilesWrapper.class);
        FileManager fileManager = new FileManager(filesWrapper);
        Path path = Paths.get("./file");

        IOException exception = new IOException();
        doThrow(exception).when(filesWrapper).createDirectories(path);

        fileManager.ensureDirectoryExists(path);

        newHandler.flush();
        assertThat(outputStream.toString(), containsString("WARNING: Failed to write directory: ./file"));
        assertThat(outputStream.toString(), containsString("Error message: " + exception));
    }
}
