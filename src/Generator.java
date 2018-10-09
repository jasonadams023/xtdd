import java.io.File;
import java.io.IOException;

class Generator {
    static void generate() {
        try {
            File file = new File("./generated/file.java");
            boolean result = file.createNewFile();
            if (result == true) {
                System.out.println("created new file");
            } else {
                System.out.println("did not create new file");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
