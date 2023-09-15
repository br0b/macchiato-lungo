package expressions;

import interpreter.Memory;

public abstract class Expression {
    // Higher number - lower priority
    // 0 is the highest possible priority
    abstract protected int evaluationPriority();

    public int compareTo(Expression other, Memory memory) throws EvaluationException {
        int value = evaluate(memory);
        int otherValue = other.evaluate(memory);

        return value - otherValue;
    }

    abstract public int evaluate(Memory memory) throws EvaluationException;

    @Override
    abstract public String toString();
}