package generator;

import fileManager.FileManager;
import javaClass.JavaClass;
import javaClass.JavaClassFactory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class WriteClassFilesTest {
    @Test
    void should_WriteFileForEachJavaClass() {
        File directory = mock(File.class);
        FileManager fileManager = mock(FileManager.class);
        JavaClassFactory javaClassFactory = mock(JavaClassFactory.class);
        Generator generator = new Generator(directory, fileManager, javaClassFactory);

        String directoryPath = "./example";
        willReturn(directoryPath).given(directory).getPath();

        JavaClass javaClass1 = mock(JavaClass.class);
        String name1 = "Example";
        String fileText1 = "Some text";
        willReturn(name1).given(javaClass1).getName();
        willReturn(fileText1).given(javaClass1).toString();
        Path path1 = Paths.get(directoryPath + "/src/" + name1 + ".java");

        JavaClass javaClass2 = mock(JavaClass.class);
        String name2 = "Different";
        String fileText2 = "Some different text";
        willReturn(name2).given(javaClass2).getName();
        willReturn(fileText2).given(javaClass2).toString();
        Path path2 = Paths.get(directoryPath + "/src/" + name2 + ".java");

        List<JavaClass> javaClasses = new ArrayList<>();
        javaClasses.add(javaClass1);
        javaClasses.add(javaClass2);

        generator.javaClasses = javaClasses;

        generator.writeFiles();

        verify(fileManager, times(1)).writeFile(path1, fileText1);
        verify(fileManager, times(1)).writeFile(path2, fileText2);
    }
}
