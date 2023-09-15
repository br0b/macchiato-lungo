package expressions;

import interpreter.Memory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstantTest {

    @Test
    void evaluate() {
        assertEquals(42, Constant.of(42).evaluate(new Memory()));
    }
}