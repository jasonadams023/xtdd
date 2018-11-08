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
            builder.append(getHeader());

            if(!returnType.equals("void")) {
                builder.append("return null;\n");
            }

            builder.append(getFooter());
        }

        return builder.toString();
    }

    private String getHeader() {
        return "static " + returnType + " " + name + "() {\n";
    }

    private String getFooter() {
        return "}\n";
    }

    public boolean equals(Object o) {
        if (o instanceof Function) {
            Function compareTo = (Function) o;
            return name.equals(compareTo.name);
        }

        return false;
    }
}
