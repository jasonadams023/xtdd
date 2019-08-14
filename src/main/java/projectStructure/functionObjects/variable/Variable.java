package projectStructure.functionObjects.variable;

public class Variable {
    public String type;
    public Object value;

    public Variable(String type, Object value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return value.getClass().getSimpleName();
    }

    public Object getObject() {
        return value;
    }
}
