package macchiatosyntax.instructions;

import expressions.Constant;
import interpreter.CallStack;
import interpreter.Memory;
import macchiatosyntax.declarations.Declaration;
import macchiatosyntax.declarations.VariableDeclaration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssignmentTest {

    @Test
    void run() {
        Declaration declaration = new VariableDeclaration('a', Constant.of(0));
        Assignment assignment = new Assignment('a', Constant.of(42));

        Memory memory = new Memory();
        CallStack callStack = new CallStack(memory);
        memory.newScope();

        assertDoesNotThrow(() -> declaration.run(callStack, memory));
        assertDoesNotThrow(() -> assignment.run(callStack, memory));
        assertDoesNotThrow(() -> assertEquals(memory.getVariableValue('a'), 42));
    }
}