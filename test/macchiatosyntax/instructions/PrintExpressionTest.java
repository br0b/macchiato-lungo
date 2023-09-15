package macchiatosyntax.instructions;

import expressions.Addition;
import expressions.Constant;
import interpreter.CallStack;
import interpreter.Memory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PrintExpressionTest {

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
        PrintExpression printExpression = new PrintExpression(Addition.of(Constant.of(1), Constant.of(1)));

        Memory memory = new Memory();
        CallStack callStack = new CallStack(memory);

        assertDoesNotThrow(() -> printExpression.run(callStack, memory));

        assertEquals("2\n", outContent.toString());
    }
}