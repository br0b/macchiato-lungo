package macchiatosyntax.instructions;

import expressions.EvaluationException;
import expressions.Expression;
import interpreter.Memory;
import macchiatosyntax.MacchiatoSyntaxIterator;
import macchiatosyntax.ifconditions.IfCondition;

import java.util.ArrayDeque;

public class IfElseInstruction extends IfInstruction {
    private final ArrayDeque<Instruction> ifElseInstructions;

    public IfElseInstruction(IfCondition ifCondition, Expression firstExpression,
                             Expression secondExpression, ArrayDeque<Instruction> instructions,
                             ArrayDeque<Instruction> ifElseInstructions) {
        super(ifCondition, firstExpression, secondExpression, instructions);
        this.ifElseInstructions = ifElseInstructions;
    }

    @Override
    protected MacchiatoSyntaxIterator iterator(Memory memory) throws EvaluationException {
        if (ifCondition.isMet(firstExpression.compareTo(secondExpression, memory))) {
            return new IfIterator(instructions);
        }
        else {
            return new IfIterator(ifElseInstructions);
        }
    }

    @Override
    public String printSyntax() {
        return super.printSyntax() + " else { ... }";
    }
}
