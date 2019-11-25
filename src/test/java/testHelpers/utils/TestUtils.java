package testHelpers.utils;

import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertAll;

public class TestUtils {
    public static void orderedContainsAssert(List<String> expectedList, List<String> data) {
        String[] dataLines = data.toArray(new String[0]);
        orderedContainsAssertBase(expectedList, dataLines);
    }

    public static void orderedContainsAssert(List<String> expectedList, String data) {
        String[] dataLines = data.split("\n");
        orderedContainsAssertBase(expectedList, dataLines);
    }

    private static void orderedContainsAssertBase(List<String> expectedList, String[] dataLines) {
        int indexOfFirstMatch = getIndexOfFirstMatch(dataLines, expectedList.get(0));
        List<Executable> assertions = new ArrayList<>();

        for (int i = 0; i < expectedList.size(); i++) {
            String expectedLine = expectedList.get(i);
            String dataLine = dataLines[i  + indexOfFirstMatch];

            assertions.add(() -> assertThat(dataLine, containsString(expectedLine)));
        }

        assertAll(assertions);
    }

    private static int getIndexOfFirstMatch(String[] dataLines, String expected) {
        int indexOfFirstMatch = 0;

        for (int j = 0; j < dataLines.length - 1; j++) {
            String dataLine = dataLines[j];
            if (dataLine.contains(expected)) {
                indexOfFirstMatch = j;
                break;
            }
        }
        return indexOfFirstMatch;
    }
}
