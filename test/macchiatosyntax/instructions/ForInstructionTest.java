package macchiatosyntax.instructions;

import expressions.Constant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ForInstructionTest {

    @Test
    void printSyntax() {
        assertDoesNotThrow(() -> {
            ForInstruction forInstruction = new ForInstructionBuilder()
                    .setLoopIteratorID('i')
                    .setNumberOfIterations(Constant.of(1))
                    .print(Constant.of(42))
                    .build();
            assertEquals(forInstruction.printSyntax(), "for i 1");
        });
    }
}