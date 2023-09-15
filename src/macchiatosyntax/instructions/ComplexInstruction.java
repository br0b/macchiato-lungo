package macchiatosyntax.instructions;

import expressions.EvaluationException;
import interpreter.CallStack;
import interpreter.Memory;
import macchiatosyntax.MacchiatoSyntaxIterator;

import java.util.ArrayDeque;

/**
 * When run, a complex instruction returns an iterator to its nested instructions.
 */
public abstract class ComplexInstruction implements Instruction {
    protected ArrayDeque<Instruction> instructions;

    public ComplexInstruction() {
        instructions = new ArrayDeque<>();
    }

    public ComplexInstruction(ArrayDeque<Instruction> instructions) {
        this.instructions = instructions.clone();
    }

    @Override
    public void run(CallStack callStack, Memory memory) throws InstructionSyntaxException {
        assert callStack != null;

        try {
            callStack.push(iterator(memory));
        }
        catch (EvaluationException e) {
            throw new InstructionSyntaxException(this);
        }
    }

    protected abstract MacchiatoSyntaxIterator iterator(Memory memory) throws EvaluationException;
}
