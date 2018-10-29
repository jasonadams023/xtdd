package function;

public class Function {
    String className;
    String name;
    String returnType;

    public Function() {
        this.className = "";
        this.name = "";
        this.returnType = "void";
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        if (!name.equals("")) {
            builder.append(returnType);
            builder.append(" ");
            builder.append(name);
            builder.append("() {\n}\n");
        }

        return builder.toString();
    }

    void setName(String name) {
        this.name = name;
    }

    void setReturnType(String returnType) {
        this.returnType = returnType;
    }
}
