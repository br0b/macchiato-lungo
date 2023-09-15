package macchiatosyntax;

import interpreter.Memory;
import macchiatosyntax.instructions.Block;

import java.util.List;
import java.util.stream.Collectors;

public class Procedure {
    private final List<Character> parameters;
    private final Block body;

    public Procedure(final List<Character> parameters,
                     final Block body) throws Exception {
        if (!areParametersValid(parameters)) {
            throw new Exception("Invalid parameters.");
        }

        this.parameters = parameters;
        this.body = body;
    }

    public MacchiatoSyntaxIterator iterator(Memory memory) {
        return body.iterator(memory);
    }

    private boolean areParametersValid(final List<Character> parameters) {
        boolean[] isCharInParameterList = new boolean[26];

        for (char parameter : parameters) {
            if (!Character.isLowerCase(parameter)) return false;

            int charID = parameter - 97;

            if (isCharInParameterList[charID]) {
                return false;
            }

            isCharInParameterList[charID] = true;
        }

        return true;
    }

    public List<Character> getParameterIDs() {
        return parameters;
    }

    public String printProcedureParameters() {
        return "(" +
                parameters.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(", ")) +
                ')';
    }
}
