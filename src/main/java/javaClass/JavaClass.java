package javaClass;

import java.io.File;

public class JavaClass {
    File file;
    private String name;

    public JavaClass(File file) {
        this.file = file;
        String fileName = file.getName();
        name = fileName.substring(0, fileName.length() - 9);
    }

    public String toString() {
        return  "class " + name  + " {\n}\n";
    }
}
