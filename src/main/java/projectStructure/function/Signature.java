package projectStructure.function;

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
}
