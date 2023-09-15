package macchiatosyntax.instructions;

import expressions.Constant;
import interpreter.CallStack;
import interpreter.Memory;
import macchiatosyntax.declarations.ProcedureDeclaration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProcedureInvocationTest {

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void run() {
        assertDoesNotThrow(() -> {
            ProcedureDeclaration procedureDeclaration =
                    new ProcedureDeclaration("test", List.of(), new BlockBuilder()
                            .print(Constant.of(42))
                            .build());

            ProcedureInvocation procedureInvocation =
                    new ProcedureInvocation("test", List.of());

            Memory memory = new Memory();
            CallStack callStack = new CallStack(memory);
            memory.newScope();

            procedureDeclaration.run(callStack, memory);
            procedureInvocation.run(callStack, memory);
            callStack.next();

            assertEquals("42\n", outContent.toString());
        });
    }
}