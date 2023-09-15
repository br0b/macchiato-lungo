package expressions;

import interpreter.Memory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DivisionTest {

    @Test
    void evaluate() {
        assertThrows(EvaluationException.class,
                () -> Division.of(Constant.of(42), Constant.of(0)).evaluate(new Memory()));
    }
}