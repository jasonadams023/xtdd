package javaClass;

import fileManager.FileManager;
import fileManager.FilesWrapper;

public class JavaClassFactory {
    private FileManager fileManager;

    public JavaClassFactory() {
        FilesWrapper filesWrapper = new FilesWrapper();
        this.fileManager = new FileManager(filesWrapper);
    }

    public JavaClassFactory(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public JavaClass newJavaClass(String name) {
        return new JavaClass(name, fileManager);
    }
}
