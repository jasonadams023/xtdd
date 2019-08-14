package projectStructure.functionObjects.variable;

public class Variable {
    private Object value;

    public Variable(Object value) {
        this.value = value;
    }

    public String getType() {
        return value.getClass().getSimpleName();
    }

    public Object getObject() {
        return value;
    }
}
