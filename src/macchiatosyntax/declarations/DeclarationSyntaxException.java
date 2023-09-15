package macchiatosyntax.declarations;

import macchiatosyntax.MacchiatoSyntaxException;

public class DeclarationSyntaxException extends MacchiatoSyntaxException {
    public DeclarationSyntaxException(Declaration declaration) {
        super(declaration);
    }
}
