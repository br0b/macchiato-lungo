package macchiatosyntax.instructions;

import expressions.Constant;
import interpreter.CallStack;
import interpreter.Memory;
import macchiatosyntax.ifconditions.Equals;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IfElseInstructionTest {

    @Test
    void run() {
        assertDoesNotThrow(() -> {
            IfElseInstruction ifElseInstruction = new IfElseInstructionBuilder()
                    .setIfCondition(new Equals())
                    .setFirstExpression(Constant.of(0))
                    .setSecondExpression(Constant.of(1))
                    .blockElse(new BlockBuilder()
                            .declareVariable('a', Constant.of(42))
                            .build())
                    .build();

            Memory memory = new Memory();
            CallStack callStack = new CallStack(memory);
            memory.newScope();

            ifElseInstruction.run(callStack, memory);
            callStack.next();
            callStack.next();

            assertEquals(memory.getVariableValue('a'), 42);
        });
    }
}