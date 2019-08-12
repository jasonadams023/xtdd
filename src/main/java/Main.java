import fileManager.FileManager;
import fileManager.FilesWrapper;
import generator.Generator;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        FilesWrapper filesWrapper = new FilesWrapper();
        FileManager fileManager = new FileManager(filesWrapper);
        Generator generator = new Generator(new File(args[0]), fileManager);
        generator.generate();
    }
}
