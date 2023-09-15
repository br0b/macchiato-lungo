package macchiatosyntax.instructions;

import expressions.EvaluationException;
import expressions.Expression;
import interpreter.CallStack;
import interpreter.Memory;

public class PrintExpression extends SimpleInstruction {
    public PrintExpression(Expression expression) {
        super(expression);
    }

    @Override
    public void run(CallStack callStack, Memory memory) throws InstructionSyntaxException {
        try {
            System.out.println(expression.evaluate(memory));
        }
        catch (EvaluationException e) {
            throw new InstructionSyntaxException(this);
        }
    }

    @Override
    public String printSyntax() {
        return "print " + expression;
    }
}
