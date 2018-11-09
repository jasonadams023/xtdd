package TestParser;

import Requirement.Requirement;
import fileManager.FileManager;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

class TestParser {
    private FileManager fileManager;

    static final String startFlag = "//beginning of classes to generate";
    static final String endFlag = "//end of classes to generate";

    TestParser(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    List<Requirement> parseTestFile(Path path) {
        List<Requirement> requirements = new ArrayList<>();
        List<String> lines = fileManager.readAllLines(path);

        for (String line : lines) {
            String className = getClassNameFromImport(line);
            if (className != null) {
                requirements.add(new Requirement(className));
            }
        }

        return requirements;
    }

    private String getClassNameFromImport(String line) {
        String[] split = line.split(Pattern.quote("."));
        String last = split[split.length - 1];
        String className = last.split(Pattern.quote(";"))[0];

        if (className.contains(" ") || className.equals("")) {
            return null;
        }

        return className;
    }
}
