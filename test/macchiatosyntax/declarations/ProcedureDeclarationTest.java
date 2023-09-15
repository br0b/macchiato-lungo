package macchiatosyntax.declarations;

import interpreter.CallStack;
import interpreter.Memory;
import macchiatosyntax.instructions.Block;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayDeque;
import java.util.List;

class ProcedureDeclarationTest {

    @Test
    void run() {
        ProcedureDeclaration procedureDeclaration = null;

        try {
            procedureDeclaration = new ProcedureDeclaration("test",
                    List.of(),
                    new Block(new ArrayDeque<>(), new ArrayDeque<>()));
        }
        catch (Exception e) {
            assert(false);
        }

        assertNotEquals(procedureDeclaration, null);

        Memory memory = new Memory();
        CallStack callStack = new CallStack(memory);
        memory.newScope();

        try {
            procedureDeclaration.run(callStack, memory);
        }
        catch (Exception e) {
            assert(false);
        }

        assertDoesNotThrow(() -> memory.getProcedure("test"));
    }
}