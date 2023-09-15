package macchiatosyntax.instructions;

import expressions.Expression;

public class ForInstructionBuilder extends ComplexInstructionBuilder<ForInstruction, ForInstructionBuilder> {
    private Character loopIteratorID;
    private Expression numberOfIterations;

    public ForInstructionBuilder() {
        super();
        loopIteratorID = null;
        numberOfIterations = null;
    }

    public ForInstructionBuilder setLoopIteratorID(Character loopIteratorID) {
        this.loopIteratorID = loopIteratorID;
        return getThis();
    }

    public ForInstructionBuilder setNumberOfIterations(Expression numberOfIterations) {
        this.numberOfIterations = numberOfIterations;
        return getThis();
    }

    @Override
    protected ForInstructionBuilder getThis() {
        return this;
    }

    @Override
    public ForInstruction build() throws Exception {
        if (loopIteratorID == null) {
            throw new Exception("ID of the loop iterator must be set.");
        } else if (numberOfIterations == null) {
            throw new Exception("Number of iterations must be set.");
        }

        return new ForInstruction(loopIteratorID, numberOfIterations, instructions);
    }
}
