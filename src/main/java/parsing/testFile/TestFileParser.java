package parsing.testFile;

import projectStructure.classObjects.classRequirement.ClassRequirement;
import projectStructure.functionObjects.functionRequirement.FunctionRequirement;
import fileManager.FileManager;
import parsing.testCase.TestCaseParser;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TestFileParser {
    private FileManager fileManager;
    private List<String> classNames;
    private List<ClassRequirement> classRequirements;

    public TestFileParser(FileManager fileManager) {
        this.fileManager = fileManager;
        this.classNames = new ArrayList<>();
        this.classRequirements = new ArrayList<>();
    }

    public List<ClassRequirement> parseTestFile(Path path) {
        List<String> lines = fileManager.readAllLines(path);
        this.classNames.clear();
        this.classRequirements.clear();

        getClassNames(lines);
        setClassRequirements();
        setFunctionRequirements(lines);

        return classRequirements;
    }

    private void getClassNames(List<String> lines) {
        for (String line : lines) {
            if (line.contains("class")) {
                String[] words = line.split(" ");
                String className = words[1].substring(0, words[1].length() - 4);
                classNames.add(className);
            }
        }
    }

    private void setClassRequirements() {
        for(String className : classNames) {
            classRequirements.add(ClassRequirement.create(className));
        }
    }

    private void setFunctionRequirements(List<String> lines) {
        List<List<String>> testCaseSets = getTestCaseSets(lines);

        for(List<String> testLines : testCaseSets) {
            for(ClassRequirement classRequirement : classRequirements) {
                String className = classRequirement.name;
                FunctionRequirement functionRequirement = TestCaseParser.parse(testLines, className);

                if (functionRequirement != null) {
                    classRequirement.addFunctionRequirement(functionRequirement);
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
