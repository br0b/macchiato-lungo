package expressions;

import interpreter.Memory;
import interpreter.ScopeException;

public class Variable extends Expression {
    private final char variableID;

    public Variable(char variableID) {
        this.variableID = variableID;
    }

    public static Variable named(char variableID) {
        return new Variable(variableID);
    }

    @Override
    protected int evaluationPriority() {
        return 0;
    }

    @Override
    public int evaluate(Memory memory) throws EvaluationException {
        try {
            return memory.getVariableValue(variableID);
        }
        catch (ScopeException scopeException) {
            throw new EvaluationException();
        }
    }

    @Override
    public String toString() {
        return String.valueOf(variableID);
    }
}
