package macchiatosyntax;

import interpreter.CallStack;
import interpreter.Memory;
import macchiatosyntax.declarations.DeclarationSyntaxException;
import macchiatosyntax.instructions.InstructionSyntaxException;

/**
 * A user can create a Macchiato program using objects of MacchiatoSyntax.
 */
public interface MacchiatoSyntax {
    void run(CallStack callStack, Memory memory) throws InstructionSyntaxException, DeclarationSyntaxException;

    String printSyntax();
}
