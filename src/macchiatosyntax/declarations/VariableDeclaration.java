package macchiatosyntax.declarations;

import expressions.EvaluationException;
import expressions.Expression;
import interpreter.CallStack;
import interpreter.Memory;
import interpreter.ScopeException;

/**
 * A declaration is not an instruction.
 */
public class VariableDeclaration implements Declaration {
    final char variableID;
    final Expression expression;

    public VariableDeclaration(char variableID, Expression expression) {
        this.variableID = variableID;
        this.expression = expression;
    }

    @Override
    public void run(CallStack callStack, Memory memory) throws DeclarationSyntaxException {
        try {
            int value = expression.evaluate(memory);
            memory.declareVariable(variableID, value);
        }
        catch (EvaluationException | ScopeException exception) {
            throw new DeclarationSyntaxException(this);
        }
    }

    @Override
    public String printSyntax() {
        return "var " + variableID + " " + expression;
    }
}
