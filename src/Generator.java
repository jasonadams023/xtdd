import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class Generator {
    static void generate(File directory) {
        File testDirectory = new File(directory.getPath() + "/test");
        String[] testFileNames = testDirectory.list();

        for (String testFileName : testFileNames) {
            String name = testFileName.substring(0, testFileName.length() - 9);
            createClass(name, directory.getPath() + "/src");
        }
    }

    static void createClass(String name, String targetDirectory) {
        Path path = Paths.get(targetDirectory + "/" + name + ".java");

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
