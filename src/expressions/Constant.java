package expressions;

import interpreter.Memory;

public class Constant extends Expression {
    final int value;

    public Constant(int value) {
        this.value = value;
    }

    public static Constant of(int value) {
        return new Constant(value);
    }

    @Override
    protected int evaluationPriority() {
        return 0;
    }

    @Override
    public int evaluate(Memory memory) {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
