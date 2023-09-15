package macchiatosyntax;

public abstract class MacchiatoSyntaxException extends Exception {
    public MacchiatoSyntaxException(MacchiatoSyntax macchiatoSyntax) {
        super(macchiatoSyntax.printSyntax());
    }
}
