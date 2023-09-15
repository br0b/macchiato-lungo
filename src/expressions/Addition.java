package expressions;

import interpreter.Memory;

public class Addition extends BinaryOperation {
    public Addition(Expression left, Expression right) {
        super(left, right);
    }

    public static Addition of(Expression left, Expression right) { return new Addition(left, right); }

    @Override
    protected char operator() {
        return '+';
    }

    @Override
    protected int evaluationPriority() {
        return 2;
    }

    @Override
    public int evaluate(Memory memory) throws EvaluationException {
        return left.evaluate(memory) + right.evaluate(memory);
    }
}
