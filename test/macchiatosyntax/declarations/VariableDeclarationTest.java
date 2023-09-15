package macchiatosyntax.declarations;

import expressions.Constant;
import interpreter.CallStack;
import interpreter.Memory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VariableDeclarationTest {

    @Test
    void run() {
        VariableDeclaration variableDeclaration = new VariableDeclaration('a', Constant.of(42));

        Memory memory = new Memory();
        CallStack callStack = new CallStack(memory);
        memory.newScope();

        assertDoesNotThrow(() -> variableDeclaration.run(callStack, memory));

        assertDoesNotThrow(() -> memory.getVariableValue('a'));
    }
}