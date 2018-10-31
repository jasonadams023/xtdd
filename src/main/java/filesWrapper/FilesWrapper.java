package filesWrapper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FilesWrapper {
    FilesInterface files;

    FilesWrapper(FilesInterface files) {
        this.files = files;
    }

    void writeFile(Path path, String text) {
        try {
            files.write(path, text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    List<String> readAllLines(Path path) {
        try {
            return files.readAllLines(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

}
