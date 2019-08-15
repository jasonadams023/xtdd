package parsing.testCase;

import parsing.variable.VariableParser;
import projectStructure.variable.Variable;
import projectStructure.functionObjects.signature.Signature;
import projectStructure.functionObjects.functionRequirement.FunctionRequirement;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TestCaseParser {
    public static FunctionRequirement parse(List<String> lines, String className) {
        String functionName = "";
        String returnType = "void";
        Object returnValue = null;
        List<Variable> variables = new ArrayList<>();

        for(String line : lines) {
            if (!line.contains(className) && line.contains(" = ")) {
                variables.add(VariableParser.parseFromLine(line));
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
            inputTypes.add(variable.getType());
        }

        Signature signature = new Signature(functionName, returnType, inputTypes);

        return FunctionRequirement.create(signature, variables, returnValue);
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
        String returnValue;
        String args = line.split(Pattern.quote("("))[1];
        String returnValueString = args.split(Pattern.quote(","))[0];
        returnValue = returnValueString.replaceAll("\"", "");

        Object object = null;

        if (!returnType.equals("void")) {
            try {
                object = VariableParser.dynamicallyCreateObject("java.lang." + returnType, returnValue);
            } catch (ReflectiveOperationException e) {
                System.out.println(e);
            }
        }

        if (object != null && object.getClass().getSimpleName().equals("String")) {
            object = "\"" + object + "\"";
        }

        return object;
    }
}
