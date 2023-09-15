package expressions;

import interpreter.Memory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubtractionTest {

    @Test
    void evaluate() {
        assertDoesNotThrow(() ->
                assertEquals(42, Subtraction.of(Constant.of(100), Constant.of(58))
                        .evaluate(new Memory())));
    }
}