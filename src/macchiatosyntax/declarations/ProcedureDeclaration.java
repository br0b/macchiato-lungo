package macchiatosyntax.declarations;

import interpreter.CallStack;
import interpreter.Memory;
import interpreter.ScopeException;
import macchiatosyntax.Procedure;
import macchiatosyntax.instructions.Block;

import java.util.List;

public class ProcedureDeclaration implements Declaration {
    private final String procedureName;
    private final Procedure procedure;

    public ProcedureDeclaration(String procedureName, List<Character> variableIDs, Block body) throws Exception {
        if (!isProcedureNameValid(procedureName)) {
            throw new Exception("Invalid procedure name.");
        }
        this.procedureName = procedureName;
        procedure = new Procedure(variableIDs, body);
    }

    @Override
    public void run(CallStack callStack, Memory memory) throws DeclarationSyntaxException {
        try {
            memory.declareProcedure(procedureName, procedure);
        }
        catch (ScopeException e) {
            throw new DeclarationSyntaxException(this);
        }
    }

    @Override
    public String printSyntax() {
        return "proc " + procedureName + " " + procedure.printProcedureParameters();
    }

    private boolean isProcedureNameValid(final String name) {
        if (name.isBlank()) {
            return false;
        }

        for (int i = 0; i < name.length(); i++) {
            if (!Character.isLowerCase(name.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}
