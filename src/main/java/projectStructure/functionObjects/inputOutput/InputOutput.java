package projectStructure.functionObjects.inputOutput;

import projectStructure.variable.Variable;

import java.util.List;

public class InputOutput {
    private List<Variable> inputs;
    private Object returnValue;

    public InputOutput (List<Variable> inputs, Object returnValue) {
        this.inputs = inputs;
        this.returnValue = returnValue;
    }

    //TODO: replace these getters with equals() and toString()
    public Object getReturnValue() {
        return returnValue;
    }

    public List<Variable> getInputs() {
        return inputs;
    }

    public boolean equals(Object o) {
        if (o instanceof InputOutput) {
            InputOutput compareTo = (InputOutput) o;
            if (this.inputs.equals(compareTo.inputs) && this.returnValue.equals(compareTo.returnValue)) {
                return true;
            }

            return false;
        }

        return false;
    }
}
