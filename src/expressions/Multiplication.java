package expressions;

import interpreter.Memory;

public class Multiplication extends BinaryOperation {
    public Multiplication(Expression left, Expression right) {
        super(left, right);
    }

    public static Multiplication of(Expression left, Expression right) { return new Multiplication(left, right); }

    @Override
    protected char operator() {
        return '*';
    }

    @Override
    protected int evaluationPriority() {
        return 1;
    }

    @Override
    public int evaluate(Memory memory) throws EvaluationException {
        return left.evaluate(memory) * right.evaluate(memory);
    }
}
