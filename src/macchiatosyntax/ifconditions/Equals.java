package macchiatosyntax.ifconditions;

public class Equals implements IfCondition {
    @Override
    public boolean isMet(int difference) {
        return difference == 0;
    }

    @Override
    public String printSymbol() {
        return "=";
    }
}
