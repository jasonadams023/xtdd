package projectStructure.functionObjects.signature;

import java.util.ArrayList;
import java.util.List;

public class Signature {
    private String name;
    private String returnType;
    private List<String> inputTypes;

    public Signature(String name, String returnType, List<String> inputTypes) {
        this.name = name;
        this.returnType = returnType;

        if(inputTypes == null) {
            inputTypes = new ArrayList<>();
        }

        this.inputTypes = inputTypes;
    }

    public String toString() {
        return "static " + returnType + " " + name + "(" + argsToString() + ")";
    }

    private String argsToString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < inputTypes.size(); i++) {
            if(i > 0) {
                builder.append(", ");
            }

            builder.append(inputTypes.get(i));
            builder.append(" arg");
            builder.append(i + 1);
        }

        return builder.toString();
    }

    public boolean equals(Object o) {
        if (o instanceof Signature) {
            Signature compareTo = (Signature) o;
            if(!compareTo.name.equals(name)) {
                return false;
            }

            if(!compareTo.returnType.equals(returnType)) {
                return false;
            }

            return compareTo.inputTypes.equals(inputTypes);
        }

        return false;
    }

    public boolean hasReturn() {
        return !returnType.equals("void");
    }
}
