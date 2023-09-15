package macchiatosyntax.instructions;

import expressions.Constant;
import interpreter.CallStack;
import interpreter.Memory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainProgramBlockTest {

    @Test
    void run() {
        MainProgramBlock mainProgramBlock = new MainProgramBlockBuilder()
                .declareVariable('a', Constant.of(42))
                .build();

        Memory memory = new Memory();
        CallStack callStack = new CallStack(memory);

        mainProgramBlock.run(callStack, memory);

        assertDoesNotThrow(() -> {
            callStack.next();
            assertEquals(memory.getVariableValue('a'), 42);
        });
    }
}