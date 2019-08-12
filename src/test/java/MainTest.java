import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MainTest {
    private File exampleDirectory = new File("./example");
    private File exampleSourceDirectory = new File(exampleDirectory.getPath() + "/src");

    @BeforeEach
    @AfterAll
    void cleanup() {
        for(File file: exampleSourceDirectory.listFiles()) {
            if (!file.isDirectory()) {
                file.delete();
            }
        }
    }

    @Test
    void should_RunGenerate() {
        String[] args = new String[2];
        args[0] = exampleDirectory.toString();

        Main.main(args);

        assert(exampleSourceDirectory.listFiles().length > 0);
    }
}
