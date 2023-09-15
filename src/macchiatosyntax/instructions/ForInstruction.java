package macchiatosyntax.instructions;

import expressions.Constant;
import expressions.EvaluationException;
import expressions.Expression;
import interpreter.Memory;
import macchiatosyntax.MacchiatoSyntax;
import macchiatosyntax.MacchiatoSyntaxIterator;
import macchiatosyntax.declarations.VariableDeclaration;

import java.util.ArrayDeque;
import java.util.Collections;

public class ForInstruction extends ComplexInstruction {
    private final char loopIteratorID;
    private final Expression numOfIterationsExpression;

    public ForInstruction(char loopIteratorID, Expression numOfIterationsExpression,
                          ArrayDeque<Instruction> instructions) {
        super(instructions);
        this.loopIteratorID = loopIteratorID;
        this.numOfIterationsExpression = numOfIterationsExpression;
    }

    @Override
    public MacchiatoSyntaxIterator iterator(Memory memory) throws EvaluationException {
        return new ForIterator(memory);
    }

    private class ForIterator implements MacchiatoSyntaxIterator {
        private final int numOfIterations;

        private int currentIteration;

        public ForIterator(Memory memory) throws EvaluationException {
            numOfIterations = numOfIterationsExpression.evaluate(memory);
            currentIteration = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIteration < numOfIterations;
        }

        @Override
        public MacchiatoSyntax next() {
            if (!hasNext())
                return null;

            MacchiatoSyntax macchiatoSyntax = peek();

            currentIteration++;

            return macchiatoSyntax;
        }

        @Override
        public MacchiatoSyntax peek() {
            if (!hasNext())
                return null;

            VariableDeclaration loopIteratorDeclaration = new VariableDeclaration(loopIteratorID, new Constant(currentIteration));
            return new Block(new ArrayDeque<>(Collections.singleton(loopIteratorDeclaration)), instructions);
        }

    }

    @Override
    public String printSyntax() {
        return "for " + loopIteratorID + " " + numOfIterationsExpression;
    }
}
