package expressions;

import interpreter.Memory;

public class Division extends BinaryOperation {
    public Division(Expression left, Expression right) {
        super(left, right);
    }

    public static Division of(Expression left, Expression right) { return new Division(left, right); }

    @Override
    protected char operator() {
        return '/';
    }

    @Override
    protected int evaluationPriority() {
        return 1;
    }

    @Override
    public int evaluate(Memory memory) throws EvaluationException {
        int rightVal = right.evaluate(memory);

        if (rightVal == 0) throw new EvaluationException();

        return left.evaluate(memory) / rightVal;
    }
}
