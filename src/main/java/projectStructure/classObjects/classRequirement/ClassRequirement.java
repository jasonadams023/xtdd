package projectStructure.classObjects.classRequirement;

import projectStructure.functionObjects.functionRequirement.FunctionRequirement;

import java.util.ArrayList;
import java.util.List;

public class ClassRequirement {
    public String name;
    public List<FunctionRequirement> functionRequirements;

    public ClassRequirement(String name) {
        this.name = name;
        this.functionRequirements = new ArrayList<>();
    }

    public static ClassRequirement create(String name) {
        return new ClassRequirement(name);
    }

    public boolean equals(Object o) {
        if (o instanceof ClassRequirement) {
            ClassRequirement compareTo = (ClassRequirement) o;

            if (this.name.equals(compareTo.name) && this.functionRequirements.equals(compareTo.functionRequirements)) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    public void addFunctionRequirement(FunctionRequirement functionRequirement) {
        this.functionRequirements.add(functionRequirement);
    }
}
