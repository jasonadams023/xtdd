package filesWrapper;

import java.io.IOException;
import java.nio.file.OpenOption;
import java.nio.file.Path;

public interface FilesInterface {
    Path write(Path path, byte[] bytes, OpenOption... options) throws IOException;
}
