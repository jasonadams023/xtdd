import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class Generator {
    static void generate(File directory) {
        File testDirectory = new File(directory.getPath() + "/test");
        String[] classNames = testDirectory.list();

        for (String className : classNames) {
            String name = className.substring(0, className.length() - 9);
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
}
