package expressions;

import interpreter.Memory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModuloTest {

    @Test
    void evaluate() {
        assertDoesNotThrow(() ->
                assertEquals(2, Modulo.of(Constant.of(6), Constant.of(4)).evaluate(new Memory())));
    }
}