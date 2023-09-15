package expressions;

import interpreter.Memory;

public class Subtraction extends BinaryOperation {
    public Subtraction(Expression left, Expression right) {
        super(left, right);
    }

    public static Subtraction of(Expression left, Expression right) { return new Subtraction(left, right); }

    @Override
    protected char operator() {
        return '-';
    }

    @Override
    protected int evaluationPriority() {
        return 2;
    }

    @Override
    public int evaluate(Memory memory) throws EvaluationException {
        return left.evaluate(memory) - right.evaluate(memory);
    }
}
