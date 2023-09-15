package macchiatosyntax.instructions;

import expressions.EvaluationException;
import expressions.Expression;
import interpreter.Memory;
import macchiatosyntax.MacchiatoSyntax;
import macchiatosyntax.MacchiatoSyntaxIterator;
import macchiatosyntax.ifconditions.IfCondition;

import java.util.ArrayDeque;

public class IfInstruction extends ComplexInstruction {
    protected IfCondition ifCondition;
    protected Expression firstExpression;
    protected Expression secondExpression;

    public IfInstruction(IfCondition ifCondition, Expression firstExpression,
                         Expression secondExpression, ArrayDeque<Instruction> instructions) {
        super(instructions);
        this.ifCondition = ifCondition;
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }

    @Override
    protected MacchiatoSyntaxIterator iterator(Memory memory) throws EvaluationException {
        if (ifCondition.isMet(firstExpression.compareTo(secondExpression, memory))) {
            return new IfIterator(instructions);
        }
        else {
            return new EmptyIterator();
        }
    }
    
    protected static class IfIterator implements MacchiatoSyntaxIterator {
        final Instruction[] instructions;
        int position;

        public IfIterator(ArrayDeque<Instruction> instructions) {
            this.instructions = instructions.toArray(new Instruction[]{});
            position = 0;
        }

        @Override
        public MacchiatoSyntax peek() {
            return instructions[position];
        }

        @Override
        public boolean hasNext() {
            return position < instructions.length;
        }

        @Override
        public MacchiatoSyntax next() {
            if (!hasNext())
                return null;

            position++;
            return instructions[position - 1];
        }
    }
    private static class EmptyIterator implements MacchiatoSyntaxIterator {
        @Override
        public MacchiatoSyntax next() {
            return null;
        }

        @Override
        public MacchiatoSyntax peek() {
            return null;
        }

        @Override
        public boolean hasNext() {
            return false;
        }
    }

    @Override
    public String printSyntax() {
        return "if (" + firstExpression + " " + ifCondition.printSymbol() + " " + secondExpression + ") then { ... }";
    }
}
