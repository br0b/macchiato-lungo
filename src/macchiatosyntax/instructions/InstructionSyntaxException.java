package macchiatosyntax.instructions;

import macchiatosyntax.MacchiatoSyntaxException;

public class InstructionSyntaxException extends MacchiatoSyntaxException {
    public InstructionSyntaxException(Instruction instruction) {
        super(instruction);
    }
}
