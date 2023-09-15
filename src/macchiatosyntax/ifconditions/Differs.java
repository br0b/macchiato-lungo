package macchiatosyntax.ifconditions;

public class Differs implements IfCondition {
    @Override
    public boolean isMet(int difference) {
        return difference != 0;
    }

    @Override
    public String printSymbol() {
        return "<>";
    }
}
