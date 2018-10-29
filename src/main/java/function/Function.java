package function;

public class Function {
    String name;
    String returnType;

    public Function() {
        this.name = "";
        this.returnType = "void";
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        if (!name.equals("")) {
            builder.append("static ");
            builder.append(returnType);
            builder.append(" ");
            builder.append(name);
            builder.append("() {\n}\n");
        }

        return builder.toString();
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object o) {
        if (o instanceof Function) {
            Function compareTo = (Function) o;
            return name.equals(compareTo.name);
        }

        return false;
    }

    void setReturnType(String returnType) {
        this.returnType = returnType;
    }
}
