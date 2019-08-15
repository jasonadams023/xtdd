package projectStructure.variable;

public class Variable {
    private Object value;

    public Variable(Object value) {
        this.value = value;
    }

    public static Variable create(Object value) {
        Object output = value;

        if (value.getClass().equals(String.class)) {
            output = "\"" + value + "\"";
        }

        return new Variable(output);
    }

    public String getType() {
        return value.getClass().getSimpleName();
    }

    public Object getObject() {
        return value;
    }
}
