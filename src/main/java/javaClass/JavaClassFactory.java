package javaClass;

import fileManager.FileManager;
import fileManager.FilesWrapper;

public class JavaClassFactory {
    public JavaClass newJavaClass(String name) {
        FilesWrapper filesWrapper = new FilesWrapper();
        FileManager fileManager = new FileManager(filesWrapper);
        return new JavaClass(name, fileManager);
    }
}
