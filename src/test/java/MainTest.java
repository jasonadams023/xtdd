import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MainTest {
    private File exampleDirectory = new File("./example");
    private File exampleMainDirectory = new File(exampleDirectory.getPath() + "/src/main");

    @BeforeEach
    @AfterAll
    void cleanup() {
        for(File file: exampleMainDirectory.listFiles()) {
            if (!file.isDirectory()) {
                file.delete();
            }
        }
    }

    @Test
    void should_RunGenerate() {
        String[] args = new String[1];
        args[0] = exampleDirectory.toString();

        Main.main(args);

        assert(exampleMainDirectory.listFiles().length > 0);
    }
}
