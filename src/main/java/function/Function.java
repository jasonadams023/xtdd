package function;

public class Function {
    String name;

    public Function() {
        this.name = "";
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        if (!name.equals("")) {
            builder.append("void ");
            builder.append(name);
            builder.append("() {\n}\n");
        }

        return builder.toString();
    }

    void setName(String name) {
        this.name = name;
    }
}
