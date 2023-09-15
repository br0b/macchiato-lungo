package macchiatosyntax.instructions;

import expressions.Expression;

/**
 * It isn't possible to step into a simple instruction. When run, a simple operation is executed and the macchiato
 * syntax iterator moves on to the next instruction.
 */
public abstract class SimpleInstruction implements Instruction {
    protected Expression expression;

    public SimpleInstruction() {
        this.expression = null;
    }

    public SimpleInstruction(Expression expression) {
        this.expression = expression;
    }
}
