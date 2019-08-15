package parsing.variable;

import projectStructure.variable.Variable;

import java.lang.reflect.Constructor;
import java.util.regex.Pattern;

public class VariableParser {
    public static Variable parseFromAssignment(String line) {
        String className = extractClassNameFromAssignment(line);
        String valueString = extractValueStringFromAssignment(line);

        Object object = createObject(className, valueString);

        return new Variable(object);
    }

    public static Object parseFromAssert(String writtenClassName, String line) {
        String value = extractValueStringFromAssert(line);
        String className = enrichClassName(writtenClassName);

        Object object = createObject(className, value);

        if (object.getClass().getSimpleName().equals("String")) {
            object = "\"" + object + "\"";
        }

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
        return line.split(Pattern.quote("="))[1].trim().split(Pattern.quote(";"))[0];
    }

    private static Object createObject(String className, String valueString) {
        Object object;

        try {
            object = dynamicallyCreateObject(className, valueString);
        } catch (ReflectiveOperationException e) {
            System.out.println(e);
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
