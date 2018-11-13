package TestParser;

import Requirement.Requirement;
import Requirement.FunctionRequirement;
import fileManager.FileManager;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TestParser {
    private FileManager fileManager;
    private List<String> classNames;
    private List<Requirement> requirements;

    public static final String startFlag = "//beginning of classes to generate";
    public static final String endFlag = "//end of classes to generate";

    public TestParser(FileManager fileManager) {
        this.fileManager = fileManager;
        this.requirements = new ArrayList<>();
        this.classNames = new ArrayList<>();
    }

    public List<Requirement> parseTestFile(Path path) {
        List<String> lines = fileManager.readAllLines(path);

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
                requirements.add(new Requirement(className, null));
            }
        }

        setFunctionRequirements(lines);

        return requirements;
    }

    private String getClassNameFromImport(String line) {
        String[] split = line.split(Pattern.quote("."));
        String last = split[split.length - 1];
        String className = last.split(Pattern.quote(";"))[0];

        return className;
    }

    private void setFunctionRequirements(List<String> lines) {
        for (String line : lines) {
            for (String className : classNames) {
                if (line.contains(className + ".")) {
                    String returnType = extractReturnTypeFromLine(line);
                    String functionName = extractFunctionNameFromLine(line);
                    FunctionRequirement functionRequirement = new FunctionRequirement(functionName, returnType);
                    requirements.add(new Requirement(className, functionRequirement));
                }
            }
        }
    }

    private String extractReturnTypeFromLine(String line) {
        String returnType = "void";

        if (line.contains("=")) {
            returnType = line.trim().split(Pattern.quote(" "))[0];
        }

        return returnType;
    }

    private String extractFunctionNameFromLine(String line) {
        String[] lineParts = line.split(Pattern.quote(" "));
        String fullCall = lineParts[lineParts.length - 1];
        String functionCall = fullCall.split(Pattern.quote("."))[1];
        String name = functionCall.split(Pattern.quote("("))[0];

        return name;
    }
}
