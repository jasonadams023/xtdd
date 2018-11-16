package requirement;

import projectStructure.Variable.Variable;

import java.util.ArrayList;
import java.util.List;

public class FunctionRequirement {
    public String name;
    public String returnType;
    public Object returnValue;
    public List<Variable> inputs;

    public FunctionRequirement(String name, String returnType) {
        this.name = name;
        this.returnType = returnType;
        if (inputs == null) {
            this.inputs = new ArrayList<>();
        }
    }

    public FunctionRequirement(String name, String returnType, Object returnValue) {
        this(name, returnType);
        this.returnValue = returnValue;
    }

    public FunctionRequirement(String name, String returnType, List<Variable> inputs, Object returnValue) {
        this(name, returnType, returnValue);
        this.inputs = inputs;
    }
}
