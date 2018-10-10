import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class Generator {
    private final File directory;

    Generator(File targetDirectory) {
        directory = targetDirectory;
    }

    void generate() {
        File testDirectory = new File(directory.getPath() + "/test");
        String[] testFileNames = testDirectory.list();

        for (String testFileName : testFileNames) {
            String name = testFileName.substring(0, testFileName.length() - 9);
            createClass(name);
        }
    }

    void createClass(String name) {
        Path path = Paths.get(directory.getPath() + "/src/" + name + ".java");

        String output = "class " + name + " {\n" +
                "    static void example() {\n" +
                "    }\n" +
                "}";
        byte[] outputBytes = output.getBytes();

        try {
            Files.write(path, outputBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
