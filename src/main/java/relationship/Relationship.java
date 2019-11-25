package relationship;

import projectStructure.functionObjects.inputOutput.InputOutput;
import projectStructure.variable.Variable;

import java.util.List;

class Relationship {
    static String finder(List<InputOutput> inputOutputs) {
        InputOutput firstSet = inputOutputs.get(0);
        Variable firstInput = firstSet.getInputs().get(0);
        Variable firstOutput = firstSet.getReturnValue();

        if (firstInput.equals(firstOutput)) {
            return "one to one";
        } else if(firstInput.getType().equals("Integer") && firstOutput.getType().equals("Integer")) {
            Integer inputInt = (Integer) firstInput.getObject();
            Integer outputInt = (Integer) firstOutput.getObject();

            if (inputInt + 1 == outputInt) {
                return "plus one";
            }
        }

        return "none found";
    }
}
