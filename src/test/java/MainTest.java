import org.junit.jupiter.api.Test;

import java.io.File;

class MainTest {
    private File exampleDirectory = new File("./example");
    private File exampleSourceDirectory = new File(exampleDirectory.getPath() + "/src");

    @Test
    void should_RunGenerate() {
        String[] args = new String[2];
        args[0] = exampleDirectory.toString();

        if (exampleSourceDirectory.exists()) {
            for (File file : exampleSourceDirectory.listFiles()) {
                if (!file.isDirectory()) {
                    file.delete();
                }
            }
        }

        Main.main(args);

        assert(exampleSourceDirectory.exists());
        assert(exampleSourceDirectory.listFiles().length > 0);
    }
}
