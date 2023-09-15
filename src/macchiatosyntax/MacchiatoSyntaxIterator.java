package macchiatosyntax;

/**
 * A MacchiatoSyntaxIterator is used to iterate over a program and it's complex instructions.
 */
public interface MacchiatoSyntaxIterator {
    boolean hasNext();
    MacchiatoSyntax next();
    MacchiatoSyntax peek();
}
