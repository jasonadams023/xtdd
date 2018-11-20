package projectStructure.function.signature;

import java.util.List;

public class Signature {
    public String name;
    public String returnType;
    public List<String> inputTypes;

    public Signature(String name, String returnType, List<String> inputTypes) {
        this.name = name;
        this.returnType = returnType;
        this.inputTypes = inputTypes;
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

            if(compareTo.inputTypes != null) {
                return compareTo.inputTypes.equals(inputTypes);
            } else {
                return inputTypes == null;
            }
        }

        return false;
    }
}
