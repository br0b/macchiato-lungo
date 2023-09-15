package macchiatosyntax.instructions;

import expressions.Expression;

import java.util.ArrayDeque;
import java.util.List;

public class IfElseInstructionBuilder
        extends AbstractIfInstructionBuilder<IfElseInstruction, IfElseInstructionBuilder> {
    private final ArrayDeque<Instruction> ifElseInstructions;

    public IfElseInstructionBuilder() {
        super();
        ifElseInstructions = new ArrayDeque<>();
    }

    public IfElseInstructionBuilder assignElse(final char variableID, final Expression value) {
        ifElseInstructions.push(new Assignment(variableID, value));
        return getThis();
    }

    public IfElseInstructionBuilder blockElse(final Block block) {
        ifElseInstructions.add(block);
        return getThis();
    }

    public IfElseInstructionBuilder invokeElse(final String name, final List<Expression> parameterValues) {
        ifElseInstructions.add(new ProcedureInvocation(name, parameterValues));
        return getThis();
    }

    public IfElseInstructionBuilder forInstructionElse(ForInstruction forInstruction) {
        ifElseInstructions.add(forInstruction);
        return getThis();
    }

    public IfElseInstructionBuilder ifInstructionElse(IfInstruction ifInstruction) {
        ifElseInstructions.add(ifInstruction);
        return getThis();
    }

    public IfElseInstructionBuilder ifElseInstructionElse(IfElseInstruction ifElseInstruction) {
        ifElseInstructions.add(ifElseInstruction);
        return getThis();
    }

    public IfElseInstructionBuilder printElse(Expression expression) {
        ifElseInstructions.add(new PrintExpression(expression));
        return getThis();
    }

    @Override
    protected IfElseInstructionBuilder getThis() {
        return this;
    }

    @Override
    public IfElseInstruction build() throws Exception {
        if (!areNecessaryFieldsSet()) {
            throw new Exception("Set all necessary fields for if instruction.");
        }

        return new IfElseInstruction(ifCondition, firstExpression, secondExpression, instructions, ifElseInstructions);
    }
}
