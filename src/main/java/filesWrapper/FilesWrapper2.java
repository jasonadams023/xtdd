package filesWrapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;

import java.util.List;

public class FilesWrapper2 implements FilesInterface {
    public Path write(Path path, byte[] bytes, OpenOption... options) throws IOException {
        return Files.write(path, bytes, options);
    }

    public List<String> readAllLines(Path path) throws IOException {
        return Files.readAllLines(path);
    }
}
