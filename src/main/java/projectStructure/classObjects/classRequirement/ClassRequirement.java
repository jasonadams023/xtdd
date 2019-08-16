package projectStructure.classObjects.classRequirement;

import projectStructure.functionObjects.functionRequirement.FunctionRequirement;

public class ClassRequirement {
    public String name;
    public FunctionRequirement function;

    public ClassRequirement(String name, FunctionRequirement function) {
        this.name = name;
        this.function = function;
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
