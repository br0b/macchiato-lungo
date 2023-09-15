package macchiatosyntax.instructions;

import expressions.Expression;

import java.util.ArrayDeque;
import java.util.List;

public abstract class ComplexInstructionBuilder<T extends ComplexInstruction,
                                                B extends ComplexInstructionBuilder<T, B>> {
    protected final ArrayDeque<Instruction> instructions;

    public ComplexInstructionBuilder() {
        instructions = new ArrayDeque<>();
    }

    protected abstract B getThis();

    public B assign(final char variableID, final Expression value) {
        instructions.push(new Assignment(variableID, value));
        return getThis();
    }

    public B block(final Block block) {
        instructions.add(block);
        return getThis();
    }

    public B invoke(final String name, final List<Expression> parameterValues) {
        instructions.add(new ProcedureInvocation(name, parameterValues));
        return getThis();
    }

    public B forInstruction(ForInstruction forInstruction) {
        instructions.add(forInstruction);
        return getThis();
    }

    public B ifInstruction(IfInstruction ifInstruction) {
        instructions.add(ifInstruction);
        return getThis();
    }

    public B ifElseInstruction(IfElseInstruction ifElseInstruction) {
        instructions.add(ifElseInstruction);
        return getThis();
    }

    public B print(Expression expression) {
        instructions.add(new PrintExpression(expression));
        return getThis();
    }

    public abstract T build() throws Exception;
}