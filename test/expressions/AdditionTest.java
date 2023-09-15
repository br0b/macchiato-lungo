package expressions;

import interpreter.Memory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdditionTest {

    @Test
    void evaluate() {assertDoesNotThrow(() -> assertEquals(2,
                Addition.of(Constant.of(1), Constant.of(1)).evaluate(new Memory())));
    }
}