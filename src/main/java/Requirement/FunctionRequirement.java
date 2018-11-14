package Requirement;

public class FunctionRequirement {
    public String name;
    public String returnType;
    public Object returnValue;

    public FunctionRequirement(String name, String returnType) {
        this.name = name;
        this.returnType = returnType;
    }

    public FunctionRequirement(String name, String returnType, Object returnValue) {
        this.name = name;
        this.returnType = returnType;
        this.returnValue = returnValue;
    }
}
