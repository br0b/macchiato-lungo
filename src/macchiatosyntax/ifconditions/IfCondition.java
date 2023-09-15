package macchiatosyntax.ifconditions;

/**
 * To be used with IfInstruction or IfElseInstruction.
 */
public interface IfCondition {
    boolean isMet(int difference);

    String printSymbol();
}
