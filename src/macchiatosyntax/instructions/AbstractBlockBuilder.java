package macchiatosyntax.instructions;

import expressions.Expression;
import macchiatosyntax.declarations.Declaration;
import macchiatosyntax.declarations.ProcedureDeclaration;
import macchiatosyntax.declarations.VariableDeclaration;

import java.util.ArrayDeque;
import java.util.List;

public abstract class AbstractBlockBuilder<T extends Block, B extends ComplexInstructionBuilder<T, B>>
        extends ComplexInstructionBuilder<T, B> {
    protected final ArrayDeque<Declaration> declarations;

    public AbstractBlockBuilder() {
        super();
        declarations = new ArrayDeque<>();
    }

    public B declareVariable(char variableID, Expression value) {
        declarations.add(new VariableDeclaration(variableID, value));
        return getThis();
    }

    public B declareProcedure(String name, List<Character> variableIDs, Block body) throws Exception {
        declarations.add(new ProcedureDeclaration(name, variableIDs, body));
        return getThis();
    }
}
