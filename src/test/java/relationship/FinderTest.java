package relationship;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import projectStructure.functionObjects.inputOutput.InputOutput;
import projectStructure.variable.Variable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FinderTest {
    @Test
    void should_ReturnNoneFound_WhenFailingToFindRelationship() {
        List<InputOutput> inputOutputs = new ArrayList<>();

        List<Variable> inputs1 = new ArrayList<>();
        inputs1.add(Variable.create("asdf"));
        Variable output1 = Variable.create(1);
        inputOutputs.add(new InputOutput(inputs1, output1));

        String output = Relationship.finder(inputOutputs);

        assertEquals("none found", output);
    }

    @Test
    void should_DetermineRelationship_OneToOne() {
        List<InputOutput> inputOutputs = new ArrayList<>();

        List<Variable> inputs1 = new ArrayList<>();
        inputs1.add(Variable.create(1));
        Variable output1 = Variable.create(1);
        inputOutputs.add(new InputOutput(inputs1, output1));

        String output = Relationship.finder(inputOutputs);

        assertEquals("one to one", output);
    }

    @Test
    void should_DetermineRelationship_PlusOne() {
        List<InputOutput> inputOutputs = new ArrayList<>();

        List<Variable> inputs1 = new ArrayList<>();
        inputs1.add(Variable.create(1));
        Variable output1 = Variable.create(2);
        inputOutputs.add(new InputOutput(inputs1, output1));

        String output = Relationship.finder(inputOutputs);

        assertEquals("plus one", output);
    }

    @Disabled
    @Test
    void should_DetermineRelationship_PlusTwo() {
        List<InputOutput> inputOutputs = new ArrayList<>();

        List<Variable> inputs1 = new ArrayList<>();
        inputs1.add(Variable.create(1));
        Variable output1 = Variable.create(3);
        inputOutputs.add(new InputOutput(inputs1, output1));

        String output = Relationship.finder(inputOutputs);

        assertEquals("plus two", output);
    }
}
