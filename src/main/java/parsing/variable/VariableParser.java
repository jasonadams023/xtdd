package parsing.variable;

import projectStructure.variable.Variable;

import java.lang.reflect.Constructor;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class VariableParser {
    private static Logger LOGGER = Logger.getLogger(VariableParser.class.getName());

    public static Variable parseFromAssignment(String line) {
        String className = extractClassNameFromAssignment(line);
        String valueString = extractValueStringFromAssignment(line);

        Object object = createObject(className, valueString);

        return Variable.create(object);
    }

    public static Object parseFromAssert(String writtenClassName, String line) {
        String value = extractValueStringFromAssert(line);
        String className = enrichClassName(writtenClassName);

        Object object = createObject(className, value);

        return object;
    }

    private static String extractClassNameFromAssignment(String line) {
        String writtenClassName = extractWrittenClassNameFromAssignment(line);
        return enrichClassName(writtenClassName);
    }

    private static String extractWrittenClassNameFromAssignment(String line) {
        return line.trim().split(Pattern.quote(" "))[0];
    }

    private static String enrichClassName(String type) {
        return "java.lang." + type;
    }

    private static String extractValueStringFromAssert(String line) {
        String args = line.split(Pattern.quote("("))[1];
        String returnValueString = args.split(Pattern.quote(","))[0];
        return returnValueString.replaceAll("\"", "");
    }

    private static String extractValueStringFromAssignment(String line) {
        String valueString;
        String postAssignmentOperator = line.split(Pattern.quote("="))[1].trim();
        String rawValue = postAssignmentOperator.split(Pattern.quote(";"))[0];

        if (rawValue.charAt(0) == '\"' && rawValue.charAt(rawValue.length() - 1) == '\"') {
            valueString = rawValue.substring(1, rawValue.length() - 1);
        } else {
            valueString = rawValue;
        }

        return valueString;
    }

    private static Object createObject(String className, String valueString) {
        Object object;

        try {
            object = dynamicallyCreateObject(className, valueString);
        } catch (ReflectiveOperationException e) {
            LOGGER.warning(e.toString());
            object = "";
        }

        return object;
    }

    private static Object dynamicallyCreateObject(String className, String value) throws ReflectiveOperationException {
        Class<?> dynamicClass = Class.forName(className);
        Constructor<?> cons = dynamicClass.getConstructor(String.class);
        return cons.newInstance(value);
    }
}
