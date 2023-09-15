package macchiatosyntax.instructions;

import expressions.EvaluationException;
import expressions.Expression;
import interpreter.CallStack;
import interpreter.Memory;
import interpreter.ScopeException;
import macchiatosyntax.MacchiatoSyntaxIterator;
import macchiatosyntax.Procedure;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ProcedureInvocation extends ComplexInstruction {
    private final String procedureName;
    private final List<Expression> parameterValues;
    private Procedure procedure;

    public ProcedureInvocation(final String procedureName, final List<Expression> parameterValues) {
        this.procedureName = procedureName;
        this.parameterValues = parameterValues;
        this.procedure = null;
    }

    @Override
    public void run(CallStack callStack, Memory memory) throws InstructionSyntaxException {
        assert callStack != null;

        try {
            procedure = memory.getProcedure(procedureName);
        }
        catch (ScopeException s) {
            throw new InstructionSyntaxException(this);
        }

        List<Character> procedureParameterIDs = procedure.getParameterIDs();

        if (parameterValues.size() != procedureParameterIDs.size()) {
            throw new InstructionSyntaxException(this);
        }

        memory.newScope();

        Iterator<Expression> parameterValuesIterator = parameterValues.iterator();
        for (Character parameterID : procedureParameterIDs) {
            try {
                memory.declareVariable(parameterID, parameterValuesIterator.next().evaluate(memory));
            }
            catch (EvaluationException | ScopeException e) {
                throw new InstructionSyntaxException(this);
            }
        }

        callStack.push(iterator(memory));
    }

    @Override
    protected MacchiatoSyntaxIterator iterator(Memory memory) {
        return procedure.iterator(memory);
    }


    @Override
    public String printSyntax() {
        return procedureName + " (" +
                parameterValues.stream()
                        .map(Expression::toString)
                        .collect(Collectors.joining(", "))
                + ')';
    }
}
