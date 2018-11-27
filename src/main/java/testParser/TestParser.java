package testParser;

import projectStructure.classObjects.classRequirement.ClassRequirement;
import projectStructure.functionObjects.functionRequirement.FunctionRequirement;
import fileManager.FileManager;
import testCase.TestCase;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TestParser {
    private FileManager fileManager;
    private List<String> classNames;
    private List<ClassRequirement> classRequirements;

    public static final String startFlag = "//beginning of classes to generate";
    public static final String endFlag = "//end of classes to generate";

    public TestParser(FileManager fileManager) {
        this.fileManager = fileManager;
        this.classRequirements = new ArrayList<>();
        this.classNames = new ArrayList<>();
    }

    public List<ClassRequirement> parseTestFile(Path path) {
        List<String> lines = fileManager.readAllLines(path);

        getClassNames(lines);
        setClassRequirements();
        setFunctionRequirements(lines);

        return classRequirements;
    }

    private void getClassNames(List<String> lines) {
        boolean flag = false;
        for (String line : lines) {
            if(line.equals(startFlag)) {
                flag = true;
                continue;
            }

            if (line.equals(endFlag)) {
                break;
            }

            if(flag) {
                String className = getClassNameFromImport(line);
                classNames.add(className);
            }
        }
    }

    private void setClassRequirements() {
        for(String className : classNames) {
            classRequirements.add(new ClassRequirement(className, null));
        }
    }

    private String getClassNameFromImport(String line) {
        String[] split = line.split(Pattern.quote("."));
        String last = split[split.length - 1];
        String className = last.split(Pattern.quote(";"))[0];

        return className;
    }

    private void setFunctionRequirements(List<String> lines) {
        List<List<String>> testCaseSets = getTestCaseSets(lines);

        for(List<String> testLines : testCaseSets) {
            for(String className : classNames) {
                FunctionRequirement functionRequirement = TestCase.parse(testLines, className);

                if (functionRequirement != null) {
                    classRequirements.add(new ClassRequirement(className, functionRequirement));
                }
            }
        }
    }

    private List<List<String>> getTestCaseSets(List<String> lines) {
        int nestingLevel = 0;
        List<String> testLines = new ArrayList<>();
        List<List<String>> testCaseSets = new ArrayList<>();

        for (String line : lines) {
            if(line.contains("@Test")) {
                testLines = new ArrayList<>();
                nestingLevel = 1;
                continue;
            }

            if(nestingLevel == 1) {
                testLines.add(line);
            }

            if(nestingLevel == 1 && line.trim().equals("}")) {
                testCaseSets.add(testLines);
                nestingLevel = 0;
            }
        }

        return testCaseSets;
    }
}
