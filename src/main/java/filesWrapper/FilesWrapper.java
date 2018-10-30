package filesWrapper;

import java.nio.file.Path;

class FilesWrapper {
    FilesInterface files;

    FilesWrapper(FilesInterface files) {
        this.files = files;
    }

    void writeFile(Path path, String text) {
        try {
            files.write(path, text.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
