package TestParser;

import requirement.Requirement;
import requirement.FunctionRequirement;
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

        getClassNames(lines);
        setClassRequirements();
        setFunctionRequirements(lines);

        return requirements;
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
            requirements.add(new Requirement(className, null));
        }
    }

    private String getClassNameFromImport(String line) {
        String[] split = line.split(Pattern.quote("."));
        String last = split[split.length - 1];
        String className = last.split(Pattern.quote(";"))[0];

        return className;
    }

    private void setFunctionRequirements(List<String> lines) {
        FunctionRequirement functionRequirement = null;
        String tempClassName = "";
        String returnType = "";
        int building = 0;

        for (String line : lines) {
            for (String className : classNames) {
                if (line.contains(className + ".")) {
                    returnType = extractReturnTypeFromLine(line);
                    String functionName = extractFunctionNameFromLine(line);
                    functionRequirement = new FunctionRequirement(functionName, returnType, null);

                    if (!returnType.equals("void")) {
                        tempClassName = className;
                        building = 1;
                    } else {
                        requirements.add(new Requirement(className, functionRequirement));
                    }
                }
            }

            if (building == 2) {
                building = 0;

                if (line.contains("assertEquals")) {
                    Object returnValue;

                    String args = line.split(Pattern.quote("("))[1];
                    String returnValueString = args.split(Pattern.quote(","))[0];
                    returnValue = returnValueString.replaceAll("\"", "");

                    returnValue = prepareValue(returnValue, returnType);

                    functionRequirement.returnValue = returnValue;
                }
                requirements.add(new Requirement(tempClassName, functionRequirement));
            }

            if (building == 1) {
                building++;
            }
        }

        if (building == 2) {
            requirements.add(new Requirement(tempClassName, functionRequirement));
        }
    }

    private Object prepareValue(Object value, String returnType) {
        if (returnType.equals("int")) {
            value = Integer.parseInt((String) value);
        }

        if(value instanceof String) {
            value = "\"" + value + "\"";
        }

        return value;
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
