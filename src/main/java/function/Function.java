package function;

public class Function {
    String name;
    String returnType;

    public Function(String name) {
        this.name = name;
        this.returnType = "void";
    }

    public Function(String name, String returnType) {
        this.name = name;
        this.returnType = returnType;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        if (!name.equals("")) {
            builder.append("static ");
            builder.append(returnType);
            builder.append(" ");
            builder.append(name);
            builder.append("() {\n");

            if(!returnType.equals("void")) {
                builder.append("return null;\n");
            }

            builder.append("}\n");
        }

        return builder.toString();
    }

    public boolean equals(Object o) {
        if (o instanceof Function) {
            Function compareTo = (Function) o;
            return name.equals(compareTo.name);
        }

        return false;
    }
}
