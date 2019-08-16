package projectStructure.classObjects.classRequirement;

import projectStructure.functionObjects.functionRequirement.FunctionRequirement;

import java.util.ArrayList;
import java.util.List;

public class ClassRequirement {
    public String name;
    public FunctionRequirement function;
    List<FunctionRequirement> functionRequirements;

    public ClassRequirement(String name, FunctionRequirement function) {
        this.name = name;
        this.function = function;
        this.functionRequirements = new ArrayList<>();
    }

    public static ClassRequirement create(String name) {
        return new ClassRequirement(name, null);
    }

    public boolean equals(Object o) {
        if (o instanceof ClassRequirement) {
            ClassRequirement compareTo = (ClassRequirement) o;

            if (this.name.equals(compareTo.name) && this.function.equals(compareTo.function)) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }
}
