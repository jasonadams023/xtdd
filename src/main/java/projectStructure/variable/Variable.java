package projectStructure.variable;

public class Variable {
    private Object value;

    private Variable(Object value) {
        this.value = value;
    }

    public static Variable create(Object value) {
        return new Variable(value);
    }

    public String getType() {
        return value.getClass().getSimpleName();
    }

    public Object getObject() {
        return value;
    }

    public String toString() {
        if (value.getClass().equals(String.class)) {
            return "\"" + value + "\"";
        }

        return value.toString();
    }
}
