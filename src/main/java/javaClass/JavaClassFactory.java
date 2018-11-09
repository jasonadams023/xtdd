package javaClass;

import fileManager.FileManager;

public class JavaClassFactory {
    private FileManager fileManager;

    public JavaClass newJavaClass(String name) {
        return new JavaClass(name, fileManager);
    }
}
