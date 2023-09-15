package expressions;

import interpreter.Memory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultiplicationTest {

    @Test
    void evaluate() {
        assertDoesNotThrow(() ->
                assertEquals(42, Multiplication.of(Constant.of(3), Constant.of(14))
                        .evaluate(new Memory())));
    }
}