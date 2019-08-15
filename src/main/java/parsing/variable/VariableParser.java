package parsing.variable;

import projectStructure.variable.Variable;

import java.lang.reflect.Constructor;
import java.util.regex.Pattern;

public class VariableParser {
    public static Variable parseFromAssignment(String line) {
        String className = extractClassName(line);
        String valueString = extractValueString(line);
        Object object = "";

        try {
            object = dynamicallyCreateObject(className, valueString);
        } catch (ReflectiveOperationException e) {
            System.out.println(e);
        }

        return new Variable(object);
    }

    public static Variable parseFromAssert(String type, String line) {
        Object object = "";
        String args = line.split(Pattern.quote("("))[1];
        String returnValueString = args.split(Pattern.quote(","))[0];
        String value = returnValueString.replaceAll("\"", "");

        try {
            object = dynamicallyCreateObject("java.lang." + type, value);
        } catch (ReflectiveOperationException e) {
            System.out.println(e);
        }

        if (object.getClass().getSimpleName().equals("String")) {
            object = "\"" + object + "\"";
        }

        return new Variable(object);
    }

    private static String extractClassName(String line) {
        String type = line.trim().split(Pattern.quote(" "))[0];
        return "java.lang." + type;
    }

    private static String extractValueString(String line) {
        return line.split(Pattern.quote("="))[1].trim().split(Pattern.quote(";"))[0];
    }

    public static Object dynamicallyCreateObject(String className, String value) throws ReflectiveOperationException {
        Class<?> dynamicClass = Class.forName(className);
        Constructor<?> cons = dynamicClass.getConstructor(String.class);
        return cons.newInstance(value);
    }
}
