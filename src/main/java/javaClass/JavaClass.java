package javaClass;

import java.io.File;

public class JavaClass {
    File file;
    String name;

    public JavaClass(File file) {
        this.file = file;
        this.name = generateName();
    }

    public String toString() {
        return  "class " + name  + " {\n}\n";
    }

    String generateName() {
        String fileName = file.getName();
        return fileName.substring(0, fileName.length() - 9);
    }
}
