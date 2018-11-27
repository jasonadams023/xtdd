package requirements;

public class ClassRequirement {
    public String name;
    public FunctionRequirement function;

    public ClassRequirement(String name, FunctionRequirement function) {
        this.name = name;
        this.function = function;
    }
}
