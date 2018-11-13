package Requirement;

public class Requirement {
    public String className;
    public FunctionRequirement function;

    public Requirement(String className, FunctionRequirement function) {
        this.className = className;
        this.function = function;
    }
}
