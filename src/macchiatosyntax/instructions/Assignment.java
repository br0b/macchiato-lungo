package macchiatosyntax.instructions;

import expressions.EvaluationException;
import expressions.Expression;
import interpreter.CallStack;
import interpreter.Memory;
import interpreter.ScopeException;

public class Assignment extends SimpleInstruction {
    private final char variableID;

    public Assignment(char variableID, Expression expression) {
        super(expression);
        this.variableID = variableID;
    }

    @Override
    public void run(CallStack callStack, Memory memory) throws InstructionSyntaxException {
        try {
            memory.assignVariable(variableID, expression.evaluate(memory));
        }
        catch (EvaluationException | ScopeException exception) {
            throw new InstructionSyntaxException(this);
        }
    }

    @Override
    public String printSyntax() {
        return variableID + " := " + expression;
    }
}
