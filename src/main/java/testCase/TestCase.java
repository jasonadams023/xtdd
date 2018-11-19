package testCase;

import projectStructure.Variable.Variable;
import projectStructure.function.Signature;
import requirement.FunctionRequirement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TestCase {
    public static FunctionRequirement parse(List<String> lines, String className) {
        String functionName = "";
        String returnType = "void";
        Object returnValue = null;
        List<Variable> variables = new ArrayList<>();

        for(String line : lines) {
            if (!line.contains(className) && line.contains(" = ")) {
                variables.add(extractVariableFromLine(line));
            }

            if (line.contains(className + ".")) {
                returnType = extractReturnTypeFromLine(line);
                functionName = extractFunctionNameFromLine(line);
            }

            if (line.contains("assertEquals")) {
                returnValue = extractReturnValueFromLine(returnType, line);
            }
        }

        if (functionName.equals("")) {
            return null;
        }

        List<String> inputTypes = new ArrayList<>();
        for(Variable variable : variables) {
            inputTypes.add(variable.type);
        }

        Signature signature = new Signature(functionName, returnType, inputTypes);

        return FunctionRequirement.create(signature, variables, returnValue);
    }

    private static Variable extractVariableFromLine(String line) {
        String type = line.trim().split(Pattern.quote(" "))[0];

        return new Variable(type);
    }

    private static String extractReturnTypeFromLine(String line) {
        String returnType = "void";

        if (line.contains("=")) {
            returnType = line.trim().split(Pattern.quote(" "))[0];
        }

        return returnType;
    }

    private static String extractFunctionNameFromLine(String line) {
        if(line.contains("=")) {
            line = line.split(Pattern.quote("="))[1];
        }

        line = line.split(Pattern.quote("."))[1];
        line = line.split(Pattern.quote("("))[0];

        return line;
    }

    private static Object extractReturnValueFromLine(String returnType, String line) {
        Object returnValue;
        String args = line.split(Pattern.quote("("))[1];
        String returnValueString = args.split(Pattern.quote(","))[0];
        returnValue = returnValueString.replaceAll("\"", "");

        returnValue = prepareValue(returnValue, returnType);
        return returnValue;
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
