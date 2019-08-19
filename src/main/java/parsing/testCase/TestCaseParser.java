package parsing.testCase;

import parsing.variable.VariableParser;
import projectStructure.variable.Variable;
import projectStructure.functionObjects.signature.Signature;
import projectStructure.functionObjects.functionRequirement.FunctionRequirement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TestCaseParser {
    public static FunctionRequirement parse(List<String> lines, String className) {
        String functionName = "";
        String returnType = "void";
        List<Variable> inputs = new ArrayList<>();
        Variable returnValue = Variable.create(null);

        for(String line : lines) {
            if (!line.contains(className) && line.contains(" = ")) {
                inputs.add(VariableParser.parseFromAssignment(line));
            }

            if (line.contains(className + ".")) {
                returnType = extractReturnTypeFromLine(line);
                functionName = extractFunctionNameFromLine(line);
            }

            if (line.contains("assertEquals")) {
                returnValue = VariableParser.parseFromAssert(returnType, line);
            }
        }

        if (functionName.equals("")) {
            return null;
        }

        List<String> inputTypes = new ArrayList<>();
        for(Variable variable : inputs) {
            inputTypes.add(variable.getType());
        }

        Signature signature = new Signature(functionName, returnType, inputTypes);

        return FunctionRequirement.create(signature, inputs, returnValue);
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
}
