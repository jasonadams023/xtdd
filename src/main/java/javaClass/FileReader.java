package javaClass;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileReader {
    List<String> readAllLines(Path path) throws IOException;
}
