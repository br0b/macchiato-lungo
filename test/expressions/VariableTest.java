package expressions;

import interpreter.CallStack;
import interpreter.Memory;
import macchiatosyntax.declarations.VariableDeclaration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VariableTest {

    @Test
    void evaluate() {
        VariableDeclaration variableDeclaration = new VariableDeclaration('a', Constant.of(42));

        Memory memory = new Memory();
        CallStack callStack = new CallStack(memory);
        memory.newScope();

        assertDoesNotThrow(() -> {
            variableDeclaration.run(callStack, memory);

            assertEquals(42, Variable.named('a').evaluate(memory));
        });
    }
}