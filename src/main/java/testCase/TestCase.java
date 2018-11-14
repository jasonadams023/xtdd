package testCase;

import requirement.FunctionRequirement;

import java.util.List;
import java.util.regex.Pattern;

class TestCase {
    static FunctionRequirement parse(List<String> lines, String className) {
        String functionName = "";
        String returnType = "void";
        Object returnValue = null;

        for(String line : lines) {
            if (line.contains(className + ".")) {
                returnType = extractReturnTypeFromLine(line);
                functionName = extractFunctionNameFromLine(line);
            }

            if (line.contains("assertEquals")) {
                String args = line.split(Pattern.quote("("))[1];
                String returnValueString = args.split(Pattern.quote(","))[0];
                returnValue = returnValueString.replaceAll("\"", "");

                returnValue = prepareValue(returnValue, returnType);
            }
        }

        if (functionName.equals("")) {
            return null;
        }

        return new FunctionRequirement(functionName, returnType, returnValue);
    }

    private static String extractReturnTypeFromLine(String line) {
        String returnType = "void";

        if (line.contains("=")) {
            returnType = line.trim().split(Pattern.quote(" "))[0];
        }

        return returnType;
    }

    private static String extractFunctionNameFromLine(String line) {
        String[] lineParts = line.split(Pattern.quote(" "));
        String fullCall = lineParts[lineParts.length - 1];
        String functionCall = fullCall.split(Pattern.quote("."))[1];
        return functionCall.split(Pattern.quote("("))[0];
    }

    private static Object prepareValue(Object value, String returnType) {
        if (returnType.equals("int")) {
            value = Integer.parseInt((String) value);
        }

        if(value instanceof String) {
            value = "\"" + value + "\"";
        }

        return value;
    }
}
