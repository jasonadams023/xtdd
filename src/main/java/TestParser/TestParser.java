package TestParser;

import Requirement.Requirement;
import fileManager.FileManager;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TestParser {
    private FileManager fileManager;

    public static final String startFlag = "//beginning of classes to generate";
    public static final String endFlag = "//end of classes to generate";

    public TestParser(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public List<Requirement> parseTestFile(Path path) {
        List<Requirement> requirements = new ArrayList<>();
        List<String> lines = fileManager.readAllLines(path);

        boolean flag = false;
        for (String line : lines) {

            if(line.equals(startFlag)) {
                flag = true;
                continue;
            }

            if (line.equals(endFlag)) {
                flag = false;
                continue;
            }

            if(flag) {
                String className = getClassNameFromImport(line);
                requirements.add(new Requirement(className));
            }
        }

        return requirements;
    }

    private String getClassNameFromImport(String line) {
        String[] split = line.split(Pattern.quote("."));
        String last = split[split.length - 1];
        String className = last.split(Pattern.quote(";"))[0];

        return className;
    }
}
