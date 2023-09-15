package macchiatosyntax.instructions;

import expressions.Expression;
import macchiatosyntax.ifconditions.IfCondition;

public abstract class AbstractIfInstructionBuilder<T extends IfInstruction, B extends ComplexInstructionBuilder<T, B>>
        extends ComplexInstructionBuilder<T, B> {
    protected IfCondition ifCondition;
    protected Expression firstExpression;
    protected Expression secondExpression;

    public AbstractIfInstructionBuilder() {
        ifCondition = null;
        firstExpression = null;
        secondExpression = null;
    }

    public B setIfCondition(IfCondition ifCondition) {
        this.ifCondition = ifCondition;
        return getThis();
    }

    public B setFirstExpression(Expression firstExpression) {
        this.firstExpression = firstExpression;
        return getThis();
    }

    public B setSecondExpression(Expression secondExpression) {
        this.secondExpression = secondExpression;
        return getThis();
    }

    protected boolean areNecessaryFieldsSet() {
        return ifCondition != null && firstExpression != null && secondExpression != null;
    }
}
